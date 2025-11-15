package caixaverso.domain.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

public class Telemetria{

    private Long id;

    private String servico;

    private Integer tempoMs;

    private OffsetDateTime dataExec;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Integer getTempoMs() {
        return tempoMs;
    }

    public void setTempoMs(Integer tempoMs) {
        this.tempoMs = tempoMs;
    }

    public OffsetDateTime getDataExec() {
        return dataExec;
    }

    public void setDataExec(OffsetDateTime dataExec) {
        this.dataExec = dataExec;
    }

    public Telemetria(Long id, String servico, Integer tempoMs, OffsetDateTime dataExec) {
        this.id = id;
        this.servico = servico;
        this.tempoMs = tempoMs;
        this.dataExec = dataExec;
    }

    public Telemetria() {
    }
}
