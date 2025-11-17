package caixaverso.infrastructure.mapper;

import caixaverso.application.dto.ProdutoDTO;
import caixaverso.domain.entity.Produto;

import caixaverso.infrastructure.persistence.entity.ProdutoEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoMapper {

    public ProdutoDTO toDTO(ProdutoEntity entity) {
        if (entity == null) return null;

        return new ProdutoDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTipo(),
                entity.getRentabilidade(),
                entity.getRisco()
        );
    }

    public Produto toEntity(ProdutoDTO dto) {
        if (dto == null) return null;

        Produto p = new Produto();
        p.setId(dto.id());
        p.setNome(dto.nome());
        p.setTipo(dto.tipo());
        p.setRentabilidade(dto.rentabilidade());
        p.setRisco(dto.risco());
        return p;
    }
}
