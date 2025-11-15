package caixaverso.infrastructure.persistence.repository;

import caixaverso.infrastructure.persistence.entity.TelemetriaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.OffsetDateTime;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class TelemetriaRepository implements PanacheRepositoryBase<TelemetriaEntity, Long> {

    public TelemetriaEntity save(TelemetriaEntity entity) {
        persist(entity);
        return entity;
    }

    /**
     * Agrega telemetria por servico: retorna Object[] com [0]=servico (String), [1]=quantidade (Long), [2]=mediaTempoMs (Double)
     */
    public List<Object[]> aggregateByService(OffsetDateTime inicio, OffsetDateTime fim) {
        String jpql = "SELECT t.servico, COUNT(t), AVG(t.tempoMs) FROM TelemetriaEntity t "
                + "WHERE t.dataExec BETWEEN :inicio AND :fim GROUP BY t.servico";
        return getEntityManager()
                .createQuery(jpql, Object[].class)
                .setParameter("inicio", inicio)
                .setParameter("fim", fim)
                .getResultList();
    }

    /**
     * Lista telemetria entre datas, se necessário para detalhes.
     */
    public List<TelemetriaEntity> findBetween(OffsetDateTime inicio, OffsetDateTime fim) {
        return list("dataExec BETWEEN ?1 AND ?2", inicio, fim);
    }
}
