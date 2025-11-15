package caixaverso.application.usecase;

import caixaverso.application.dto.SimulacaoResponseDTO;
import caixaverso.domain.entity.Simulacao;
import caixaverso.infrastructure.mapper.SimulacaoMapper;
import caixaverso.infrastructure.mapper.SimulacaoResponseMapper;
import caixaverso.infrastructure.persistence.entity.ProdutoEntity;
import caixaverso.infrastructure.persistence.entity.SimulacaoEntity;
import caixaverso.infrastructure.persistence.repository.ProdutoRepository;
import caixaverso.infrastructure.persistence.repository.SimulacaoRepository;
import caixaverso.infrastructure.persistence.repository.SimulacaoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class SimulacaoUseCase {

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    SimulacaoRepositoryImpl simulacaoRepository;

    @Inject
    SimulacaoResponseMapper mapper;

    public List<SimulacaoResponseDTO> listarTodos(int page, int size) {
        List<Simulacao> lista = simulacaoRepository.listarPaginado(page, size);
        return lista.stream()
                .map(simulacao -> mapper.toResponse(
                        SimulacaoMapper.toHistoricoDTO(simulacao),
                        SimulacaoMapper.toHistoricoDTO(simulacao),
                        simulacao.getDataSimulacao()
                ))
                .toList();
    }

    public SimulacaoEntity simular(Integer idProduto, BigDecimal valor, int prazoMeses, Long clienteId) {

        ProdutoEntity produto = produtoRepository.findByIdOptional(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        validarLimites(produto, valor, prazoMeses);

        BigDecimal mensal = converterTaxaAnualParaMensal(produto.getRentabilidade());
        BigDecimal valorFinal = calcularJurosCompostos(valor, mensal, prazoMeses);

        SimulacaoEntity entity = new SimulacaoEntity();
        entity.setProdutoId(produto.getId());
        entity.setProdutoNome(produto.getNome());
        entity.setValorSolicitado(valor);
        entity.setValorFinal(valorFinal);
        entity.setPrazoMeses(prazoMeses);
        entity.setTaxaJurosAnual(produto.getRentabilidade());
        entity.setTaxaMensalEfetiva(mensal);
        entity.setDataSimulacao(OffsetDateTime.now());
        entity.setClienteId(clienteId);
        entity.setSucesso(true);

        simulacaoRepository.salvar(entity);
        return entity;
    }

    private void validarLimites(ProdutoEntity p, BigDecimal valor, int prazo) {
        if (p.getMinValor().compareTo(valor) > 0)
            throw new RuntimeException("Valor abaixo do mínimo permitido");

        if (p.getMaxValor() != null && p.getMaxValor().compareTo(valor) < 0)
            throw new RuntimeException("Valor acima do máximo permitido");

        if (prazo < p.getMinPrazo())
            throw new RuntimeException("Prazo inferior ao mínimo");

        if (p.getMaxPrazo() != null && prazo > p.getMaxPrazo())
            throw new RuntimeException("Prazo superior ao máximo");
    }

    private BigDecimal converterTaxaAnualParaMensal(BigDecimal taxaAnual) {
        return BigDecimal.valueOf(Math.pow(1 + taxaAnual.doubleValue(), 1.0 / 12.0) - 1)
                .setScale(10, RoundingMode.HALF_UP);
    }

    private BigDecimal calcularJurosCompostos(BigDecimal valor, BigDecimal taxaMensal, int meses) {
        double vf = valor.doubleValue() * Math.pow(1 + taxaMensal.doubleValue(), meses);
        return BigDecimal.valueOf(vf).setScale(2, RoundingMode.HALF_UP);
    }

    public List<Object[]> agregacaoPorProdutoDia() {
        return simulacaoRepository.aggregateByProductAndDay(
                java.time.LocalDate.now().minusDays(30),
                java.time.LocalDate.now()
        );
    }
}
