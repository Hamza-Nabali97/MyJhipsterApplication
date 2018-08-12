package edu.bzu.jhipsterapp.repository;

import edu.bzu.jhipsterapp.domain.Tile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Tile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TileRepository extends JpaRepository<Tile, Long> {

}
