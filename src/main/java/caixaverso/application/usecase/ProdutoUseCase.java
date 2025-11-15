package caixaverso.application.usecase;

import caixaverso.infrastructure.persistence.entity.ProdutoEntity;
import caixaverso.infrastructure.persistence.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class ProdutoUseCase {

    @Inject
    ProdutoRepository repository;

    public List<ProdutoEntity> listar() {
        return repository.listAllProdutos();
    }

    public ProdutoEntity buscarPorId(Integer id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public ProdutoEntity criarProduto(ProdutoEntity entity) {
        repository.persistProduto(entity);
        return entity;
    }

    public ProdutoEntity atualizar(Integer id, ProdutoEntity novo) {
        ProdutoEntity atual = buscarPorId(id);

        atual.setNome(novo.getNome());
        atual.setTipo(novo.getTipo());
        atual.setMinValor(novo.getMinValor());
        atual.setMaxValor(novo.getMaxValor());
        atual.setMinPrazo(novo.getMinPrazo());
        atual.setMaxPrazo(novo.getMaxPrazo());

        return atual;
    }

    public void deletar(Integer id) {
        ProdutoEntity produto = buscarPorId(id);
        repository.delete(produto);
    }

    public List<ProdutoEntity> sugerirProdutos(BigDecimal valor, int prazoMeses, String tipoProduto) {
        return repository.findValidByParams(valor, prazoMeses, tipoProduto);
    }
}
