package caixaverso.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "telemetria")
public class TelemetriaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "quantidade_chamadas")
    private Long quantidadeChamadas;

    @Column(name = "media_tempo_resposta_ms")
    private Long mediaTempoRespostaMs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getQuantidadeChamadas() {
        return quantidadeChamadas;
    }

    public void setQuantidadeChamadas(Long quantidadeChamadas) {
        this.quantidadeChamadas = quantidadeChamadas;
    }

    public Long getMediaTempoRespostaMs() {
        return mediaTempoRespostaMs;
    }

    public void setMediaTempoRespostaMs(Long mediaTempoRespostaMs) {
        this.mediaTempoRespostaMs = mediaTempoRespostaMs;
    }

    public TelemetriaEntity(Long id, String nome, Long quantidadeChamadas, Long mediaTempoRespostaMs) {
        this.id = id;
        this.nome = nome;
        this.quantidadeChamadas = quantidadeChamadas;
        this.mediaTempoRespostaMs = mediaTempoRespostaMs;
    }

    public TelemetriaEntity() {
    }
}