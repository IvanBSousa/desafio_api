package caixaverso.application.usecase;

import caixaverso.infrastructure.persistence.entity.HistoricoInvestimentoEntity;
import caixaverso.infrastructure.persistence.repository.HistoricoInvestimentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class HistoricoInvestimentoUseCase {

    @Inject
    HistoricoInvestimentoRepository repository;

    public HistoricoInvestimentoEntity registrarInvestimento(Long clienteId, String produto, double valor) {
        HistoricoInvestimentoEntity h = new HistoricoInvestimentoEntity();
        h.setClienteId(clienteId);
        h.setValorInvestido(valor);
        h.setProduto(produto);
        h.setData(LocalDate.now());
        repository.save(h);
        return h;
    }

    public List<HistoricoInvestimentoEntity> listarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public List<HistoricoInvestimentoEntity> listarPorPeriodo(Long clienteId, LocalDate inicio, LocalDate fim) {
        return repository.findByClienteIdBetweenDates(clienteId, inicio, fim);
    }
}
