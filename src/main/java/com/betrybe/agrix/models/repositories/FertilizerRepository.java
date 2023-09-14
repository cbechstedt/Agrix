package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Define repositório de Fertilizer.
 */
@Component
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}
