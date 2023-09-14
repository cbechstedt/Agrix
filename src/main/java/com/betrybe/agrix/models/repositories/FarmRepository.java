package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Define repositório de farm.
 */
@Component
public interface FarmRepository extends JpaRepository<Farm, Long> {

}
