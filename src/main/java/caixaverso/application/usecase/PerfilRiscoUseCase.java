package caixaverso.application.usecase;

import caixaverso.application.dto.PerfilRiscoResponseDTO;
import caixaverso.domain.entity.Investimento;
import caixaverso.infrastructure.persistence.entity.InvestimentoEntity;
import caixaverso.infrastructure.persistence.repository.InvestimentoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PerfilRiscoUseCase {

    @Inject
    InvestimentoRepositoryImpl investimentoRepository;

    /**
     * Calcula perfil de risco do cliente com base em:
     * - volume total investido
     * - frequência de movimentações
     * - preferência por liquidez/rentabilidade (derivada de tipos escolhidos)
     */
    public PerfilRiscoResponseDTO calcularPerfil(Long clienteId) {

        List<InvestimentoEntity> historico = investimentoRepository.listarPorCliente(clienteId);

        if (historico.isEmpty()) {
            return new PerfilRiscoResponseDTO(
                    clienteId,
                    "Conservador",
                    20,
                    "Sem histórico. Perfil considerado conservador por padrão."
            );
        }

        int pontuacao = 0;

        // 🔹 Volume total aplicado
        var totalAplicado = historico.stream()
                .map(i -> i.getValor())
                .reduce((a, b) -> a.add(b))
                .orElseThrow();

        if (totalAplicado.doubleValue() > 50000) pontuacao += 30;
        else if (totalAplicado.doubleValue() > 20000) pontuacao += 20;
        else pontuacao += 10;

        // 🔹 Frequência de movimentações
        int movimentacoes = historico.size();
        if (movimentacoes > 10) pontuacao += 30;
        else if (movimentacoes > 5) pontuacao += 20;
        else pontuacao += 10;

        // 🔹 Preferência (baseada no tipo do produto)
        long agressivos = historico.stream()
                .filter(i -> i.getTipo().toLowerCase().contains("fundo") ||
                        i.getTipo().toLowerCase().contains("ações") ||
                        i.getTipo().toLowerCase().contains("multimercado"))
                .count();

        if (agressivos > 5) pontuacao += 40;
        else if (agressivos > 2) pontuacao += 25;
        else pontuacao += 5;

        // 🔻 Classificação final
        String perfil;
        String descricao;

        if (pontuacao >= 70) {
            perfil = "Agressivo";
            descricao = "Busca maior rentabilidade e assume mais risco.";
        } else if (pontuacao >= 40) {
            perfil = "Moderado";
            descricao = "Equilíbrio entre segurança e rentabilidade.";
        } else {
            perfil = "Conservador";
            descricao = "Prioriza segurança e liquidez.";
        }

        return new PerfilRiscoResponseDTO(clienteId, perfil, pontuacao, descricao);
    }
}
