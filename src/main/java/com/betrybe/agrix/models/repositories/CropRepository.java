package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Define repositório de crop.
 */
@Component
public interface CropRepository extends JpaRepository<Crop, Long> {

  List<Crop> findCropsByFarmId(Long farmId);
}
