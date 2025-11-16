package caixaverso.application.usecase;

import caixaverso.application.dto.ProdutoDTO;
import caixaverso.infrastructure.mapper.ProdutoMapper;
import caixaverso.infrastructure.persistence.entity.ProdutoEntity;
import caixaverso.infrastructure.persistence.repository.ProdutoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class ProdutoUseCase {

    @Inject
    ProdutoRepositoryImpl repository;

    @Inject
    ProdutoMapper mapper;

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


        return atual;
    }

    public void deletar(Integer id) {
        ProdutoEntity produto = buscarPorId(id);
        repository.delete(produto);
    }

    public List<ProdutoEntity> sugerirProdutos(BigDecimal valor, int prazoMeses, String tipoProduto) {
        return repository.findValidByParams(valor, prazoMeses, tipoProduto);
    }

    public List<ProdutoDTO> buscarProdutosPorPerfil(String perfil) {
        return repository.buscarPorPerfil(perfil)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
