package caixaverso.domain.repository;

import caixaverso.domain.entity.Simulacao;

import java.util.List;
import java.util.Optional;

public interface SimulacaoRepository {
    Simulacao salvar(Simulacao simulacao);
    Optional<Simulacao> buscaPorId(Long id);
    List<Simulacao> listarTodos();
    void deletar(Long id);
}
