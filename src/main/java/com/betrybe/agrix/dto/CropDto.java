package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import java.time.LocalDate;

/**
 * Define DTO.
 */
public record CropDto(long id, String name, double plantedArea, long farmId, LocalDate plantedDate,
                      LocalDate harvestDate) {

  /**
   * Método para transformar entidade em dto.
   */
  public static CropDto entityToDto(Crop crop) {
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId(),
        crop.getPlantedDate(), crop.getHarvestDate());
  }

  /**
   * Método para transformar dto em entidade.
   */
  public Crop dtoToEntity() {
    Crop crop = new Crop();
    crop.setName(this.name);
    crop.setPlantedArea(this.plantedArea);
    crop.setPlantedDate(this.plantedDate);
    crop.setHarvestDate(this.harvestDate);

    Farm farm = new Farm();
    farm.setId(this.farmId);

    crop.setFarm(farm);

    return crop;
  }

}
