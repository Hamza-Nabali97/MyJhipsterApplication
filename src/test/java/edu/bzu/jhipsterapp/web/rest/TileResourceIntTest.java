package edu.bzu.jhipsterapp.web.rest;

import edu.bzu.jhipsterapp.JhipsterappApp;

import edu.bzu.jhipsterapp.domain.Tile;
import edu.bzu.jhipsterapp.repository.TileRepository;
import edu.bzu.jhipsterapp.service.TileService;
import edu.bzu.jhipsterapp.service.dto.TileDTO;
import edu.bzu.jhipsterapp.service.mapper.TileMapper;
import edu.bzu.jhipsterapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static edu.bzu.jhipsterapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TileResource REST controller.
 *
 * @see TileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterappApp.class)
public class TileResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_PLACEMENT = "AAAAAAAAAA";
    private static final String UPDATED_PLACEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_POLISH_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_POLISH_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_COST_PRICE = 1D;
    private static final Double UPDATED_COST_PRICE = 2D;

    private static final Integer DEFAULT_MINIMUM_SELLING_PRICE = 1;
    private static final Integer UPDATED_MINIMUM_SELLING_PRICE = 2;

    private static final Integer DEFAULT_INVENTORY_QUNATITY = 1;
    private static final Integer UPDATED_INVENTORY_QUNATITY = 2;

    @Autowired
    private TileRepository tileRepository;


    @Autowired
    private TileMapper tileMapper;
    

    @Autowired
    private TileService tileService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTileMockMvc;

    private Tile tile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TileResource tileResource = new TileResource(tileService);
        this.restTileMockMvc = MockMvcBuilders.standaloneSetup(tileResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tile createEntity(EntityManager em) {
        Tile tile = new Tile()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .size(DEFAULT_SIZE)
            .placement(DEFAULT_PLACEMENT)
            .polishType(DEFAULT_POLISH_TYPE)
            .costPrice(DEFAULT_COST_PRICE)
            .minimumSellingPrice(DEFAULT_MINIMUM_SELLING_PRICE)
            .inventoryQunatity(DEFAULT_INVENTORY_QUNATITY);
        return tile;
    }

    @Before
    public void initTest() {
        tile = createEntity(em);
    }

    @Test
    @Transactional
    public void createTile() throws Exception {
        int databaseSizeBeforeCreate = tileRepository.findAll().size();

        // Create the Tile
        TileDTO tileDTO = tileMapper.toDto(tile);
        restTileMockMvc.perform(post("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isCreated());

        // Validate the Tile in the database
        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeCreate + 1);
        Tile testTile = tileList.get(tileList.size() - 1);
        assertThat(testTile.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTile.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTile.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testTile.getPlacement()).isEqualTo(DEFAULT_PLACEMENT);
        assertThat(testTile.getPolishType()).isEqualTo(DEFAULT_POLISH_TYPE);
        assertThat(testTile.getCostPrice()).isEqualTo(DEFAULT_COST_PRICE);
        assertThat(testTile.getMinimumSellingPrice()).isEqualTo(DEFAULT_MINIMUM_SELLING_PRICE);
        assertThat(testTile.getInventoryQunatity()).isEqualTo(DEFAULT_INVENTORY_QUNATITY);
    }

    @Test
    @Transactional
    public void createTileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tileRepository.findAll().size();

        // Create the Tile with an existing ID
        tile.setId(1L);
        TileDTO tileDTO = tileMapper.toDto(tile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTileMockMvc.perform(post("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tile in the database
        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tileRepository.findAll().size();
        // set the field null
        tile.setName(null);

        // Create the Tile, which fails.
        TileDTO tileDTO = tileMapper.toDto(tile);

        restTileMockMvc.perform(post("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isBadRequest());

        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tileRepository.findAll().size();
        // set the field null
        tile.setCode(null);

        // Create the Tile, which fails.
        TileDTO tileDTO = tileMapper.toDto(tile);

        restTileMockMvc.perform(post("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isBadRequest());

        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tileRepository.findAll().size();
        // set the field null
        tile.setSize(null);

        // Create the Tile, which fails.
        TileDTO tileDTO = tileMapper.toDto(tile);

        restTileMockMvc.perform(post("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isBadRequest());

        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlacementIsRequired() throws Exception {
        int databaseSizeBeforeTest = tileRepository.findAll().size();
        // set the field null
        tile.setPlacement(null);

        // Create the Tile, which fails.
        TileDTO tileDTO = tileMapper.toDto(tile);

        restTileMockMvc.perform(post("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isBadRequest());

        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPolishTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tileRepository.findAll().size();
        // set the field null
        tile.setPolishType(null);

        // Create the Tile, which fails.
        TileDTO tileDTO = tileMapper.toDto(tile);

        restTileMockMvc.perform(post("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isBadRequest());

        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = tileRepository.findAll().size();
        // set the field null
        tile.setCostPrice(null);

        // Create the Tile, which fails.
        TileDTO tileDTO = tileMapper.toDto(tile);

        restTileMockMvc.perform(post("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isBadRequest());

        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMinimumSellingPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = tileRepository.findAll().size();
        // set the field null
        tile.setMinimumSellingPrice(null);

        // Create the Tile, which fails.
        TileDTO tileDTO = tileMapper.toDto(tile);

        restTileMockMvc.perform(post("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isBadRequest());

        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInventoryQunatityIsRequired() throws Exception {
        int databaseSizeBeforeTest = tileRepository.findAll().size();
        // set the field null
        tile.setInventoryQunatity(null);

        // Create the Tile, which fails.
        TileDTO tileDTO = tileMapper.toDto(tile);

        restTileMockMvc.perform(post("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isBadRequest());

        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTiles() throws Exception {
        // Initialize the database
        tileRepository.saveAndFlush(tile);

        // Get all the tileList
        restTileMockMvc.perform(get("/api/tiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tile.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.toString())))
            .andExpect(jsonPath("$.[*].placement").value(hasItem(DEFAULT_PLACEMENT.toString())))
            .andExpect(jsonPath("$.[*].polishType").value(hasItem(DEFAULT_POLISH_TYPE.toString())))
            .andExpect(jsonPath("$.[*].costPrice").value(hasItem(DEFAULT_COST_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].minimumSellingPrice").value(hasItem(DEFAULT_MINIMUM_SELLING_PRICE)))
            .andExpect(jsonPath("$.[*].inventoryQunatity").value(hasItem(DEFAULT_INVENTORY_QUNATITY)));
    }
    

    @Test
    @Transactional
    public void getTile() throws Exception {
        // Initialize the database
        tileRepository.saveAndFlush(tile);

        // Get the tile
        restTileMockMvc.perform(get("/api/tiles/{id}", tile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tile.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.toString()))
            .andExpect(jsonPath("$.placement").value(DEFAULT_PLACEMENT.toString()))
            .andExpect(jsonPath("$.polishType").value(DEFAULT_POLISH_TYPE.toString()))
            .andExpect(jsonPath("$.costPrice").value(DEFAULT_COST_PRICE.doubleValue()))
            .andExpect(jsonPath("$.minimumSellingPrice").value(DEFAULT_MINIMUM_SELLING_PRICE))
            .andExpect(jsonPath("$.inventoryQunatity").value(DEFAULT_INVENTORY_QUNATITY));
    }
    @Test
    @Transactional
    public void getNonExistingTile() throws Exception {
        // Get the tile
        restTileMockMvc.perform(get("/api/tiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTile() throws Exception {
        // Initialize the database
        tileRepository.saveAndFlush(tile);

        int databaseSizeBeforeUpdate = tileRepository.findAll().size();

        // Update the tile
        Tile updatedTile = tileRepository.findById(tile.getId()).get();
        // Disconnect from session so that the updates on updatedTile are not directly saved in db
        em.detach(updatedTile);
        updatedTile
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .size(UPDATED_SIZE)
            .placement(UPDATED_PLACEMENT)
            .polishType(UPDATED_POLISH_TYPE)
            .costPrice(UPDATED_COST_PRICE)
            .minimumSellingPrice(UPDATED_MINIMUM_SELLING_PRICE)
            .inventoryQunatity(UPDATED_INVENTORY_QUNATITY);
        TileDTO tileDTO = tileMapper.toDto(updatedTile);

        restTileMockMvc.perform(put("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isOk());

        // Validate the Tile in the database
        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeUpdate);
        Tile testTile = tileList.get(tileList.size() - 1);
        assertThat(testTile.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTile.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTile.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testTile.getPlacement()).isEqualTo(UPDATED_PLACEMENT);
        assertThat(testTile.getPolishType()).isEqualTo(UPDATED_POLISH_TYPE);
        assertThat(testTile.getCostPrice()).isEqualTo(UPDATED_COST_PRICE);
        assertThat(testTile.getMinimumSellingPrice()).isEqualTo(UPDATED_MINIMUM_SELLING_PRICE);
        assertThat(testTile.getInventoryQunatity()).isEqualTo(UPDATED_INVENTORY_QUNATITY);
    }

    @Test
    @Transactional
    public void updateNonExistingTile() throws Exception {
        int databaseSizeBeforeUpdate = tileRepository.findAll().size();

        // Create the Tile
        TileDTO tileDTO = tileMapper.toDto(tile);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTileMockMvc.perform(put("/api/tiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tile in the database
        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTile() throws Exception {
        // Initialize the database
        tileRepository.saveAndFlush(tile);

        int databaseSizeBeforeDelete = tileRepository.findAll().size();

        // Get the tile
        restTileMockMvc.perform(delete("/api/tiles/{id}", tile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tile> tileList = tileRepository.findAll();
        assertThat(tileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tile.class);
        Tile tile1 = new Tile();
        tile1.setId(1L);
        Tile tile2 = new Tile();
        tile2.setId(tile1.getId());
        assertThat(tile1).isEqualTo(tile2);
        tile2.setId(2L);
        assertThat(tile1).isNotEqualTo(tile2);
        tile1.setId(null);
        assertThat(tile1).isNotEqualTo(tile2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TileDTO.class);
        TileDTO tileDTO1 = new TileDTO();
        tileDTO1.setId(1L);
        TileDTO tileDTO2 = new TileDTO();
        assertThat(tileDTO1).isNotEqualTo(tileDTO2);
        tileDTO2.setId(tileDTO1.getId());
        assertThat(tileDTO1).isEqualTo(tileDTO2);
        tileDTO2.setId(2L);
        assertThat(tileDTO1).isNotEqualTo(tileDTO2);
        tileDTO1.setId(null);
        assertThat(tileDTO1).isNotEqualTo(tileDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tileMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tileMapper.fromId(null)).isNull();
    }
}
