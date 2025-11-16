package caixaverso.domain.repository;

import caixaverso.infrastructure.persistence.entity.ProdutoEntity;

import java.util.List;

public interface ProdutoRepository {
    List<ProdutoEntity> buscarPorRisco(String risco);

}
