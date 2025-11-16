package caixaverso.domain.repository;

import caixaverso.domain.entity.Investimento;

import java.util.List;

public interface InvestimentoRepository {

    List<Investimento> listarPorCliente(Long clienteId);
}
