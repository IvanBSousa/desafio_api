package br.caixa.application.mapper;

import br.caixa.application.dto.SimulacaoDTO;
import br.caixa.domain.entity.Simulacao;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimulacaoMapper {

    public Simulacao toEntity(SimulacaoDTO dto) {
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

    public SimulacaoDTO toDTO(Simulacao entity) {
        if (entity == null) return null;

        return new SimulacaoDTO(
                entity.getId(),
                entity.getClienteId(),
                entity.getProduto(),
                entity.getValorInvestido(),
                entity.getValorFinal(),
                entity.getPrazoMeses(),
                entity.getDataSimulacao()
        );
    }
}
