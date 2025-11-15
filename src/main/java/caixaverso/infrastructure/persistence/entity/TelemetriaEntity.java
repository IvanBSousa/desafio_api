package caixaverso.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "telemetria")
public class TelemetriaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String servico;

    @Column(name = "tempo_ms")
    private Integer tempoMs;

    @Column(name = "data_exec")
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

    public TelemetriaEntity(Long id, String servico, Integer tempoMs, OffsetDateTime dataExec) {
        this.id = id;
        this.servico = servico;
        this.tempoMs = tempoMs;
        this.dataExec = dataExec;
    }

    public TelemetriaEntity() {
    }
}
