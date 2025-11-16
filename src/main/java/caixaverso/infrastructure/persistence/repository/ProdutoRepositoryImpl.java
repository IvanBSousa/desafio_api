package caixaverso.infrastructure.persistence.repository;

import caixaverso.domain.entity.Produto;
import caixaverso.infrastructure.persistence.entity.ProdutoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoRepositoryImpl implements PanacheRepositoryBase<ProdutoEntity, Integer> {

    public List<ProdutoEntity> listAllProdutos() {
        return listAll();
    }

    public Optional<ProdutoEntity> findByIdOptional(Integer id) {
        return find("id", id).firstResultOptional();
    }

    /**
     * Busca produtos por tipo (ex: "CDB", "Fundo"). Se tipo null ou vazio, retorna todos.
     */
    public List<ProdutoEntity> findByTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            return listAll();
        }
        return list("tipo = ?1", tipo);
    }

    /**
     * Busca produtos que se adequem aos parâmetros de validação:
     * valor >= min_valor, (max_valor is null OR valor <= max_valor),
     * prazo between min_prazo and max_prazo (considera max_prazo null como sem limite).
     * Se tipoProduto for null retorna de todos os tipos.
     */
    public List<ProdutoEntity> findValidByParams(BigDecimal valor, int prazoMeses, String tipoProduto) {
        String jpql = "FROM ProdutoEntity p WHERE p.minValor <= :valor "
                + "AND (p.maxValor IS NULL OR p.maxValor >= :valor) "
                + "AND p.minPrazo <= :prazo "
                + "AND (p.maxPrazo IS NULL OR p.maxPrazo >= :prazo)";

        if (tipoProduto != null && !tipoProduto.isBlank()) {
            jpql += " AND p.tipo = :tipo";
            return getEntityManager()
                    .createQuery(jpql, ProdutoEntity.class)
                    .setParameter("valor", valor)
                    .setParameter("prazo", prazoMeses)
                    .setParameter("tipo", tipoProduto)
                    .getResultList();
        } else {
            return getEntityManager()
                    .createQuery(jpql, ProdutoEntity.class)
                    .setParameter("valor", valor)
                    .setParameter("prazo", prazoMeses)
                    .getResultList();
        }
    }

    public ProdutoEntity persistProduto(ProdutoEntity produto) {
        persist(produto);
        return produto;
    }


    public List<ProdutoEntity> buscarPorRisco(String risco) {
        return getEntityManager().createQuery("""
            SELECT p FROM ProdutoEntity p
            WHERE p.risco = :risco
        """, ProdutoEntity.class)
                .setParameter("risco", risco)
                .getResultList();
    }


    public List<Produto> buscarPorPerfil(String perfilRisco) {
        return getEntityManager().createQuery("""
            SELECT p FROM ProdutoEntity p
            WHERE p.risco = :risco
        """, Produto.class)
                .setParameter("risco", perfilRisco)
                .getResultList();
    }

}
