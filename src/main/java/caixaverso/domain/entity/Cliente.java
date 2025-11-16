package caixaverso.domain.entity;

public class Cliente {

    private Long id;

    private String perfil;

    private Integer pontuacao;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente(Long id, String perfil, Integer pontuacao, String descricao) {
        this.id = id;
        this.perfil = perfil;
        this.pontuacao = pontuacao;
        this.descricao = descricao;
    }

    public Cliente() {
    }
}
