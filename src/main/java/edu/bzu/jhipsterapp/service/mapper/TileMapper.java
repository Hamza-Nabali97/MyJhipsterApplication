package edu.bzu.jhipsterapp.service.mapper;

import edu.bzu.jhipsterapp.domain.*;
import edu.bzu.jhipsterapp.service.dto.TileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tile and its DTO TileDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface TileMapper extends EntityMapper<TileDTO, Tile> {

    @Mapping(source = "company.id", target = "companyId")
    TileDTO toDto(Tile tile);

    @Mapping(source = "companyId", target = "company")
    Tile toEntity(TileDTO tileDTO);

    default Tile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tile tile = new Tile();
        tile.setId(id);
        return tile;
    }
}
