package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.CropNotFound;
import com.betrybe.agrix.exception.FarmNotFound;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Define camada service para crop.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmRepository farmRepository;

  /**
   * Define construtor.
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmRepository farmRepository) {
    this.cropRepository = cropRepository;
    this.farmRepository = farmRepository;
  }

  /**
   * Método para criar crop.
   */
  public Crop save(Crop crop, long farmId) {
    Optional<Farm> findFarm = farmRepository.findById(farmId);
    if (findFarm.isEmpty()) {
      throw new FarmNotFound("Fazenda não encontrada!");
    }
    crop.setFarm(findFarm.get());

    return cropRepository.save(crop);
  }

  /**
   * Método para buscar crops por farm.
   */
  public List<Crop> findCropsByFarmId(long farmId) {
    Optional<Farm> findFarm = farmRepository.findById(farmId);
    if (findFarm.isEmpty()) {
      throw new FarmNotFound("Fazenda não encontrada!");
    }
    return cropRepository.findCropsByFarmId(farmId);
  }

  public List<Crop> findAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Método para buscar crop por id.
   */
  public Crop findById(long id) {
    Optional<Crop> crop = cropRepository.findById(id);
    if (crop.isEmpty()) {
      throw new CropNotFound("Plantação não encontrada!");
    }
    return crop.get();
  }

  /**
   * Método para buscar crops por data.
   */
  public List<Crop> findCropsByHarvestDate(LocalDate start, LocalDate end) {
    if (start.isAfter(end)) {
      throw new IllegalArgumentException("A data de início deve ser anterior à data de término.");
    }

    return cropRepository.findAll().stream()
        .filter(
            crop -> crop.getHarvestDate() != null && isBetween(crop.getHarvestDate(), start, end))
        .collect(Collectors.toList());
  }

  private boolean isBetween(LocalDate harvestDate, LocalDate start, LocalDate end) {
    return !harvestDate.isBefore(start) && !harvestDate.isAfter(end);
  }
}
