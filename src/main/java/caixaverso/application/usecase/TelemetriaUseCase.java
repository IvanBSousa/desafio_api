package caixaverso.application.usecase;

import caixaverso.infrastructure.persistence.entity.TelemetriaEntity;
import caixaverso.infrastructure.persistence.repository.TelemetriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class TelemetriaUseCase {

    @Inject
    TelemetriaRepository repository;

    public TelemetriaEntity registrar(String servico, long tempoMs, boolean sucesso) {
        TelemetriaEntity t = new TelemetriaEntity();
        t.setServico(servico);
        t.setTempoMs(tempoMs);
        t.setSucesso(sucesso);
        t.setDataExec(OffsetDateTime.now());
        return repository.save(t);
    }

    public List<Object[]> consolidadoPeriodo(int dias) {
        OffsetDateTime fim = OffsetDateTime.now();
        OffsetDateTime inicio = fim.minusDays(dias);
        return repository.aggregateByService(inicio, fim);
    }
}
