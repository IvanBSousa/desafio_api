package caixaverso.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "simulacao")
public class SimulacaoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;

    @Column(name = "produto_id")
    private Integer produtoId;

    @Column(name = "produto_nome")
    private String produtoNome;

    @Column(name = "valor_solicitado", precision = 18, scale = 2)
    private BigDecimal valorSolicitado;

    @Column(name = "valor_final", precision = 18, scale = 2)
    private BigDecimal valorFinal;

    @Column(name = "prazo_meses")
    private Integer prazoMeses;

    @Column(name = "taxa_juros_anual", precision = 10, scale = 6)
    private BigDecimal taxaJurosAnual;

    @Column(name = "taxa_mensal_efetiva", precision = 10, scale = 6)
    private BigDecimal taxaMensalEfetiva;

    private OffsetDateTime dataSimulacao;

    private boolean sucesso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public BigDecimal getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(BigDecimal valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public BigDecimal getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    public void setTaxaJurosAnual(BigDecimal taxaJurosAnual) {
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public BigDecimal getTaxaMensalEfetiva() {
        return taxaMensalEfetiva;
    }

    public void setTaxaMensalEfetiva(BigDecimal taxaMensalEfetiva) {
        this.taxaMensalEfetiva = taxaMensalEfetiva;
    }

    public OffsetDateTime getDataSimulacao() {
        return dataSimulacao;
    }

    public void setDataSimulacao(OffsetDateTime dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public SimulacaoEntity(Long id, Long clienteId, Integer produtoId, String produtoNome, BigDecimal valorSolicitado,
                           BigDecimal valorFinal, Integer prazoMeses, BigDecimal taxaJurosAnual,
                           BigDecimal taxaMensalEfetiva, OffsetDateTime dataSimulacao, boolean sucesso) {
        this.id = id;
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.valorSolicitado = valorSolicitado;
        this.valorFinal = valorFinal;
        this.prazoMeses = prazoMeses;
        this.taxaJurosAnual = taxaJurosAnual;
        this.taxaMensalEfetiva = taxaMensalEfetiva;
        this.dataSimulacao = dataSimulacao;
        this.sucesso = sucesso;
    }

    public SimulacaoEntity() {
    }
}
