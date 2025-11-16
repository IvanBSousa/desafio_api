package caixaverso.application.usecase;

import caixaverso.application.dto.SimulacaoAgrupadaResponseDTO;
import caixaverso.application.dto.SimulacaoHistoricoDTO;
import caixaverso.application.dto.SimulacaoRequestDTO;
import caixaverso.application.dto.SimulacaoResponseDTO;
import caixaverso.domain.entity.Simulacao;
import caixaverso.infrastructure.mapper.SimulacaoHistoricoMapper;
import caixaverso.infrastructure.mapper.SimulacaoResponseMapper;
import caixaverso.infrastructure.persistence.entity.ProdutoEntity;
import caixaverso.infrastructure.persistence.entity.SimulacaoEntity;
import caixaverso.infrastructure.persistence.repository.ProdutoRepositoryImpl;
import caixaverso.infrastructure.persistence.repository.SimulacaoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class SimulacaoUseCase {

    @Inject
    ProdutoRepositoryImpl produtoRepositoryImpl;

    @Inject
    SimulacaoRepositoryImpl simulacaoRepository;

    @Inject
    SimulacaoHistoricoMapper historicoMapper;

    @Inject
    SimulacaoResponseMapper responseMapper;

    public List<SimulacaoHistoricoDTO> listarTodos(int page, int size) {
        List<SimulacaoEntity> lista = simulacaoRepository.listarPaginado(page, size);

        return lista.stream()
                .map(historicoMapper::toDTO)
                .toList();
    }

    public SimulacaoEntity simularInterno(Long clienteId, BigDecimal valor, int prazoMeses, String nomeProduto) {

        ProdutoEntity produto = produtoRepositoryImpl.findByName(nomeProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));



        BigDecimal mensal = converterTaxaAnualParaMensal(produto.getRentabilidade());
        BigDecimal valorFinal = calcularJurosCompostos(valor, mensal, prazoMeses);

        SimulacaoEntity entity = new SimulacaoEntity();
        entity.setProduto(produto.getNome());
        entity.setValorInvestido(valor);
        entity.setValorFinal(valorFinal);
        entity.setPrazoMeses(prazoMeses);
        entity.setDataSimulacao(Instant.now());
        entity.setClienteId(clienteId);

        simulacaoRepository.salvar(entity);
        return entity;
    }

    public SimulacaoResponseDTO simular(SimulacaoRequestDTO request) {

        SimulacaoEntity entity = simularInterno(
                request.clienteId(),
                request.valorInvestido(),
                request.prazoMeses(),
                request.produto()
        );

        return responseMapper.toResponse(
            entity,
            produtoRepositoryImpl.findByName(request.produto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado")
        ));
    }


//    private void validarLimites(ProdutoEntity p, BigDecimal valor, int prazo) {
//        if (p.getMinValor().compareTo(valor) > 0)
//            throw new RuntimeException("Valor abaixo do mínimo permitido");
//
//        if (p.getMaxValor() != null && p.getMaxValor().compareTo(valor) < 0)
//            throw new RuntimeException("Valor acima do máximo permitido");
//
//        if (prazo < p.getMinPrazo())
//            throw new RuntimeException("Prazo inferior ao mínimo");
//
//        if (p.getMaxPrazo() != null && prazo > p.getMaxPrazo())
//            throw new RuntimeException("Prazo superior ao máximo");
//    }

    private BigDecimal converterTaxaAnualParaMensal(BigDecimal taxaAnual) {
        return BigDecimal.valueOf(Math.pow(1 + taxaAnual.doubleValue(), 1.0 / 12.0) - 1)
                .setScale(10, RoundingMode.HALF_UP);
    }

    private BigDecimal calcularJurosCompostos(BigDecimal valor, BigDecimal taxaMensal, int meses) {
        double vf = valor.doubleValue() * Math.pow(1 + taxaMensal.doubleValue(), meses);
        return BigDecimal.valueOf(vf).setScale(2, RoundingMode.HALF_UP);
    }

    public List<SimulacaoAgrupadaResponseDTO> agregacaoPorProdutoDia() {
        return simulacaoRepository.agruparPorProdutoDia();
    }
}
