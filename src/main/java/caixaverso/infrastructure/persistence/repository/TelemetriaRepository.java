package caixaverso.infrastructure.persistence.repository;

import caixaverso.application.dto.TelemetriaItemDTO;
import caixaverso.application.dto.TelemetriaResponseDTO;
import caixaverso.infrastructure.mapper.TelemetriaMapper;
import caixaverso.infrastructure.persistence.entity.TelemetriaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.OffsetDateTime;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class TelemetriaRepository implements PanacheRepositoryBase<TelemetriaEntity, Long> {

    @Inject
    TelemetriaMapper mapper;

    @Transactional
//    public TelemetriaItemDTO save(TelemetriaItemDTO dto) {
//
//
//
//        // garante que só buscamos exatamente o nome desejado
//        TelemetriaEntity existing = find("nome", dto.nome()).firstResult();
//
//        if (existing != null) {
//            existing.setQuantidadeChamadas(dto.quantidadeChamadas());
//
//            // média ponderada simples entre a anterior e a nova
//            existing.setMediaTempoRespostaMs(dto.mediaTempoRespostaMs());
//
//            persist(existing);
//            return dto;
//        }
//
//        // Se não encontrou → cria nova linha
//        TelemetriaEntity novo = mapper.toEntity(dto);
//        persist(novo);
//
//        return dto;
//    }
    public TelemetriaItemDTO save(TelemetriaItemDTO dto) {
//        var existing = find("nome", dto.nome()).firstResult();
//        if (existing != null) {
//            existing.setQuantidadeChamadas(existing.getQuantidadeChamadas() + dto.quantidadeChamadas());
//            existing.setMediaTempoRespostaMs(
//                    (existing.getMediaTempoRespostaMs() + dto.mediaTempoRespostaMs()) / 2
//            );
//            persist(existing);
//            return dto;
//        }

        persist(mapper.toEntity(dto));
        return dto;

    }

    /**
     * Agrega telemetria por servico: retorna Object[] com [0]=servico (String), [1]=quantidade (Long), [2]=mediaTempoMs (Double)
     */
    public List<TelemetriaResponseDTO> aggregateByService(OffsetDateTime inicio, OffsetDateTime fim) {
        String jpql = "SELECT t.servico, COUNT(t), AVG(t.tempoMs) FROM TelemetriaEntity t "
                + "WHERE t.dataExec BETWEEN :inicio AND :fim GROUP BY t.servico";
        return getEntityManager()
                .createQuery(jpql, TelemetriaResponseDTO.class)
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
