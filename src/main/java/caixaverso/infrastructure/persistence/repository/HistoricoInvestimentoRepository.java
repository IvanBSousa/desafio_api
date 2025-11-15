package caixaverso.infrastructure.persistence.repository;

import caixaverso.infrastructure.persistence.entity.HistoricoInvestimentoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class HistoricoInvestimentoRepository implements PanacheRepositoryBase<HistoricoInvestimentoEntity, Long> {

    public HistoricoInvestimentoEntity save(HistoricoInvestimentoEntity entity) {
        persist(entity);
        return entity;
    }

    public List<HistoricoInvestimentoEntity> findByClienteId(Long clienteId) {
        return list("clienteId", clienteId);
    }

    public List<HistoricoInvestimentoEntity> findByClienteIdBetweenDates(Long clienteId, LocalDate inicio, LocalDate fim) {
        return list("clienteId = ?1 AND data BETWEEN ?2 AND ?3", clienteId, inicio, fim);
    }
}
