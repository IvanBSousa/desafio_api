package caixaverso.infrastructure.persistence.repository;

import caixaverso.application.dto.SimulacaoAgrupadaDTO;
import caixaverso.domain.entity.Simulacao;
import caixaverso.domain.repository.SimulacaoRepository;
import caixaverso.infrastructure.persistence.entity.SimulacaoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class SimulacaoRepositoryImpl implements SimulacaoRepository {

    @Inject
    EntityManager em;

    @Override
    public SimulacaoEntity salvar(SimulacaoEntity simulacao) {
        if (simulacao.getId() == null) {
            em.persist(simulacao);
            return simulacao;
        }
        return em.merge(simulacao);
    }

    @Override
    public List<Simulacao> listarPaginado(int page, int size) {
        return em.createQuery("SELECT s FROM SimulacaoEntity s ORDER BY s.dataSimulacao DESC", Simulacao.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<SimulacaoAgrupadaDTO> agruparPorProdutoDia() {
        return em.createQuery("""
                SELECT new caixaverso.application.dto.SimulacaoAgrupadaDTO(
                    s.produto,
                    FUNCTION('DATE', s.dataSimulacao),
                    COUNT(s),
                    SUM(s.valorInvestido),
                    SUM(s.valorFinal)
                )
                FROM SimulacaoEntity s
                GROUP BY FUNCTION('DATE', s.dataSimulacao), s.produto
                ORDER BY FUNCTION('DATE', s.dataSimulacao) DESC
            """, SimulacaoAgrupadaDTO.class)
                .getResultList();
    }
}
