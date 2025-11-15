package caixaverso.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class ProdutoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String tipo;

    private BigDecimal rentabilidade; // anual

    private String risco;

    @Column(name = "min_valor")
    private BigDecimal minValor;

    @Column(name = "max_valor")
    private BigDecimal maxValor;

    @Column(name = "min_prazo")
    private Integer minPrazo;

    @Column(name = "max_prazo")
    private Integer maxPrazo;

    private String liquidez;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(BigDecimal rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }

    public BigDecimal getMinValor() {
        return minValor;
    }

    public void setMinValor(BigDecimal minValor) {
        this.minValor = minValor;
    }

    public BigDecimal getMaxValor() {
        return maxValor;
    }

    public void setMaxValor(BigDecimal maxValor) {
        this.maxValor = maxValor;
    }

    public Integer getMinPrazo() {
        return minPrazo;
    }

    public void setMinPrazo(Integer minPrazo) {
        this.minPrazo = minPrazo;
    }

    public Integer getMaxPrazo() {
        return maxPrazo;
    }

    public void setMaxPrazo(Integer maxPrazo) {
        this.maxPrazo = maxPrazo;
    }

    public String getLiquidez() {
        return liquidez;
    }

    public void setLiquidez(String liquidez) {
        this.liquidez = liquidez;
    }

    public ProdutoEntity(Integer id, String nome, String tipo, BigDecimal rentabilidade, String risco,
                         BigDecimal minValor, BigDecimal maxValor, Integer minPrazo, Integer maxPrazo,
                         String liquidez) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.rentabilidade = rentabilidade;
        this.risco = risco;
        this.minValor = minValor;
        this.maxValor = maxValor;
        this.minPrazo = minPrazo;
        this.maxPrazo = maxPrazo;
        this.liquidez = liquidez;
    }

    public ProdutoEntity() {
    }
}
