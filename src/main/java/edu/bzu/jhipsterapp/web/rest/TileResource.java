package edu.bzu.jhipsterapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.bzu.jhipsterapp.service.TileService;
import edu.bzu.jhipsterapp.web.rest.errors.BadRequestAlertException;
import edu.bzu.jhipsterapp.web.rest.util.HeaderUtil;
import edu.bzu.jhipsterapp.web.rest.util.PaginationUtil;
import edu.bzu.jhipsterapp.service.dto.TileDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Tile.
 */
@RestController
@RequestMapping("/api")
public class TileResource {

    private final Logger log = LoggerFactory.getLogger(TileResource.class);

    private static final String ENTITY_NAME = "tile";

    private final TileService tileService;

    public TileResource(TileService tileService) {
        this.tileService = tileService;
    }

    /**
     * POST  /tiles : Create a new tile.
     *
     * @param tileDTO the tileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tileDTO, or with status 400 (Bad Request) if the tile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tiles")
    @Timed
    public ResponseEntity<TileDTO> createTile(@Valid @RequestBody TileDTO tileDTO) throws URISyntaxException {
        log.debug("REST request to save Tile : {}", tileDTO);
        if (tileDTO.getId() != null) {
            throw new BadRequestAlertException("A new tile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TileDTO result = tileService.save(tileDTO);
        return ResponseEntity.created(new URI("/api/tiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tiles : Updates an existing tile.
     *
     * @param tileDTO the tileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tileDTO,
     * or with status 400 (Bad Request) if the tileDTO is not valid,
     * or with status 500 (Internal Server Error) if the tileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tiles")
    @Timed
    public ResponseEntity<TileDTO> updateTile(@Valid @RequestBody TileDTO tileDTO) throws URISyntaxException {
        log.debug("REST request to update Tile : {}", tileDTO);
        if (tileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TileDTO result = tileService.save(tileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tiles : get all the tiles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tiles in body
     */
    @GetMapping("/tiles")
    @Timed
    public ResponseEntity<List<TileDTO>> getAllTiles(Pageable pageable) {
        log.debug("REST request to get a page of Tiles");
        Page<TileDTO> page = tileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tiles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tiles/:id : get the "id" tile.
     *
     * @param id the id of the tileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tiles/{id}")
    @Timed
    public ResponseEntity<TileDTO> getTile(@PathVariable Long id) {
        log.debug("REST request to get Tile : {}", id);
        Optional<TileDTO> tileDTO = tileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tileDTO);
    }

    /**
     * DELETE  /tiles/:id : delete the "id" tile.
     *
     * @param id the id of the tileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteTile(@PathVariable Long id) {
        log.debug("REST request to delete Tile : {}", id);
        tileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
