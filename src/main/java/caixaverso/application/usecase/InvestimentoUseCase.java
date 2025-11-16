package caixaverso.application.usecase;

import caixaverso.application.dto.InvestimentoDTO;
import caixaverso.infrastructure.mapper.InvestimentoMapper;
import caixaverso.infrastructure.persistence.repository.InvestimentoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class InvestimentoUseCase {

    @Inject
    InvestimentoRepositoryImpl repository;

    @Inject
    InvestimentoMapper mapper;

    /**
     * Lista histórico de investimentos do cliente
     */
    public List<InvestimentoDTO> listarPorCliente(Long clienteId) {
        return repository.listarPorCliente(clienteId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
