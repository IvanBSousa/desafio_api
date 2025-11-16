package caixaverso.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HistoricoInvestimento {

    private Long id;

    private Long clienteId;

    private String tipo;

    private BigDecimal valorInvestido;

    private BigDecimal rentabilidade;

    private String produto;

    private LocalDate data;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(BigDecimal valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public BigDecimal getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(BigDecimal rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public String getProduto() { return produto; }

    public void setProduto(String produto) { this.produto = produto; }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public HistoricoInvestimento(Long id, Long clienteId, String tipo, BigDecimal valorInvestido, BigDecimal rentabilidade, LocalDate data) {
        this.id = id;
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.valorInvestido = valorInvestido;
        this.rentabilidade = rentabilidade;
        this.data = data;
    }

    public HistoricoInvestimento() {
    }
}
