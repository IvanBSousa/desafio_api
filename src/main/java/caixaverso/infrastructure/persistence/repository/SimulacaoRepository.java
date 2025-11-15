package caixaverso.infrastructure.persistence.repository;

import caixaverso.infrastructure.persistence.entity.SimulacaoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.OffsetDateTime;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class SimulacaoRepository implements PanacheRepositoryBase<SimulacaoEntity, Long> {

    public SimulacaoEntity save(SimulacaoEntity entity) {
        persist(entity);
        return entity;
    }

    public List<SimulacaoEntity> listAllSimulacoes() {
        return listAll();
    }

    public List<SimulacaoEntity> findByClienteId(Long clienteId) {
        return list("clienteId", clienteId);
    }

    /**
     * Agregacao por produto e dia (retorna lista de Object[] onde:
     * [0]=produtoNome (String), [1]=data (LocalDate), [2]=quantidade (Long), [3]=mediaValorFinal (Double/BigDecimal)
     *
     * Você pode adaptar para retornar um DTO/projection conforme preferir.
     */
    public List<Object[]> aggregateByProductAndDay(LocalDate inicio, LocalDate fim) {
        String jpql = "SELECT s.produtoNome, CAST(s.dataSimulacao as date), COUNT(s), AVG(s.valorFinal) "
                + "FROM SimulacaoEntity s WHERE CAST(s.dataSimulacao as date) BETWEEN :inicio AND :fim "
                + "GROUP BY s.produtoNome, CAST(s.dataSimulacao as date) ORDER BY s.produtoNome, CAST(s.dataSimulacao as date)";

        return getEntityManager()
                .createQuery(jpql, Object[].class)
                .setParameter("inicio", inicio)
                .setParameter("fim", fim)
                .getResultList();
    }
}
