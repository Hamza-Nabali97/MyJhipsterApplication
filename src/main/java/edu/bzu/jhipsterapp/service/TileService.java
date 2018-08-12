package edu.bzu.jhipsterapp.service;

import edu.bzu.jhipsterapp.domain.Tile;
import edu.bzu.jhipsterapp.repository.TileRepository;
import edu.bzu.jhipsterapp.service.dto.TileDTO;
import edu.bzu.jhipsterapp.service.mapper.TileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Tile.
 */
@Service
@Transactional
public class TileService {

    private final Logger log = LoggerFactory.getLogger(TileService.class);

    private final TileRepository tileRepository;

    private final TileMapper tileMapper;

    public TileService(TileRepository tileRepository, TileMapper tileMapper) {
        this.tileRepository = tileRepository;
        this.tileMapper = tileMapper;
    }

    /**
     * Save a tile.
     *
     * @param tileDTO the entity to save
     * @return the persisted entity
     */
    public TileDTO save(TileDTO tileDTO) {
        log.debug("Request to save Tile : {}", tileDTO);
        Tile tile = tileMapper.toEntity(tileDTO);
        tile = tileRepository.save(tile);
        return tileMapper.toDto(tile);
    }

    /**
     * Get all the tiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<TileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tiles");
        return tileRepository.findAll(pageable)
            .map(tileMapper::toDto);
    }


    /**
     * Get one tile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TileDTO> findOne(Long id) {
        log.debug("Request to get Tile : {}", id);
        return tileRepository.findById(id)
            .map(tileMapper::toDto);
    }

    /**
     * Delete the tile by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Tile : {}", id);
        tileRepository.deleteById(id);
    }
}
