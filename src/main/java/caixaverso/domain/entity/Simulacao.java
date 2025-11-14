package caixaverso.domain.entity;

import java.math.BigDecimal;
import java.time.Instant;

public class Simulacao {

    private int id;

    private int clientId;

    private String produto;

    private BigDecimal valorInvestido;

    private BigDecimal valorFinal;

    private int prazoMeses;

    private Instant dataSimulacao;
}
