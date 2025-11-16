package caixaverso.application.usecase;

import caixaverso.application.dto.TelemetriaResponseDTO;
import caixaverso.infrastructure.persistence.entity.TelemetriaEntity;
import caixaverso.infrastructure.persistence.repository.TelemetriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class TelemetriaUseCase {

    @Inject
    TelemetriaRepository repository;

    public TelemetriaEntity registrar(String servico, Integer tempoMs, boolean sucesso) {
        TelemetriaEntity t = new TelemetriaEntity();
        t.setServico(servico);
        t.setTempoMs(tempoMs);
        t.setSucesso(sucesso);
        t.setDataExec(OffsetDateTime.now());
        return repository.save(t);
    }

    public List<TelemetriaResponseDTO> consolidadoPeriodo(LocalDate inicio, LocalDate fim) {
        OffsetDateTime dataInicio = inicio.atStartOfDay().atOffset(OffsetDateTime.now().getOffset());
        OffsetDateTime dataFim = fim.plusDays(1).atStartOfDay().atOffset(OffsetDateTime.now().getOffset());
        return repository.aggregateByService(dataInicio, dataFim);
    }

}
