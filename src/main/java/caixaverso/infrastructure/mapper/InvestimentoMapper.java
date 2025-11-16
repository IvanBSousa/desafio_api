package caixaverso.infrastructure.mapper;

import caixaverso.application.dto.InvestimentoDTO;
import caixaverso.domain.entity.Investimento;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InvestimentoMapper {

    public InvestimentoDTO toDTO(Investimento entity) {
        if (entity == null) return null;

        return new InvestimentoDTO(
                entity.getId(),
                entity.getClienteId(),
                entity.getTipo(),
                entity.getValor(),
                entity.getRentabilidade(),
                entity.getData()
        );
    }
}
