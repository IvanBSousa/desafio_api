package caixaverso.infrastructure.persistence.entity;

import caixaverso.domain.enums.PerfilRiscoEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "perfil_risco")
public class PerfilRisco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private Long clienteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilRiscoEnum perfil;

    private int pontuacao;

    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public PerfilRiscoEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilRiscoEnum perfil) {
        this.perfil = perfil;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PerfilRisco(Integer id, Long clienteId, PerfilRiscoEnum perfil, int pontuacao, String descricao) {
        this.id = id;
        this.clienteId = clienteId;
        this.perfil = perfil;
        this.pontuacao = pontuacao;
        this.descricao = descricao;
    }

    public PerfilRisco() {
    }
}
