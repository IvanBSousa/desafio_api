package caixaverso.infrastructure.persistence.repository;

import caixaverso.domain.entity.Investimento;
import caixaverso.domain.repository.InvestimentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class InvestimentoRepositoryImpl implements InvestimentoRepository {

    @Inject
    EntityManager em;

    @Override
    public List<Investimento> listarPorCliente(Long clienteId) {
        return em.createQuery("""
                SELECT i FROM InvestimentoEntity i
                WHERE i.clienteId = :clienteId
                ORDER BY i.data DESC
            """, Investimento.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }
}
