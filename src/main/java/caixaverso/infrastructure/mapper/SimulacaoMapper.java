package caixaverso.infrastructure.mapper;

import caixaverso.application.dto.SimulacaoHistoricoDTO;
import caixaverso.domain.entity.Simulacao;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimulacaoMapper {

    public static SimulacaoHistoricoDTO toHistoricoDTO(Simulacao entity) {
        if (entity == null) return null;

        return new SimulacaoHistoricoDTO(
                entity.getId(),
                entity.getClienteId(),
                entity.getProduto(),
                entity.getValorInvestido(),
                entity.getValorFinal(),
                entity.getPrazoMeses(),
                entity.getDataSimulacao()
        );
    }

    public static toEntity(SimulacaoHistoricoDTO dto) {
        if (dto == null) return null;

        Simulacao s = new Simulacao();
        s.setId(dto.id());
        s.setClienteId(dto.clienteId());
        s.setProduto(dto.produto());
        s.setValorInvestido(dto.valorInvestido());
        s.setValorFinal(dto.valorFinal());
        s.setPrazoMeses(dto.prazoMeses());
        s.setDataSimulacao(dto.dataSimulacao());
        return s;
    }
}
