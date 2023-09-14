package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.CropNotFound;
import com.betrybe.agrix.exception.FertilizerNotFound;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Define service para Fertilizer.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository,
      CropRepository cropRepository) {
    this.fertilizerRepository = fertilizerRepository;
    this.cropRepository = cropRepository;
  }

  public Fertilizer save(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> findAll() {
    return fertilizerRepository.findAll();
  }

  /**
   * Método para buscar fertilizer por id.
   */
  public Fertilizer findById(long id) {
    Optional<Fertilizer> fertilizer = fertilizerRepository.findById(id);
    if (fertilizer.isEmpty()) {
      throw new FertilizerNotFound("Fertilizante não encontrado!");
    }
    return fertilizer.get();
  }

  /**
   * Método para associar uma crop com um fertilizer.
   */
  public void associateCropWithFertilizer(long cropId, long fertilizerId) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(() -> new CropNotFound("Plantação não encontrada!"));

    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(() -> new FertilizerNotFound("Fertilizante não encontrado!"));

    crop.getFertilizers().add(fertilizer);

    cropRepository.save(crop);
  }


}
