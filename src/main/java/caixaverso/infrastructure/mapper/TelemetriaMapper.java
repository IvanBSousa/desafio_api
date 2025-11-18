package caixaverso.infrastructure.mapper;

import caixaverso.application.dto.TelemetriaItemDTO;
import caixaverso.infrastructure.persistence.entity.TelemetriaEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelemetriaMapper {

    public TelemetriaEntity toEntity(TelemetriaItemDTO dto) {
        if (dto == null) {
            return null;
        }

        TelemetriaEntity entity = new TelemetriaEntity();
        entity.setNome(dto.nome());
        entity.setQuantidadeChamadas(dto.quantidadeChamadas());
        entity.setMediaTempoRespostaMs(dto.mediaTempoRespostaMs());
        // Adicione outros campos conforme necessário

        return entity;
    }
}
