package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * Define DTO.
 */
public record FertilizerDto(long id, String name, String brand, String composition) {

  /**
   * Método para transformar entidade em dto.
   */
  public static FertilizerDto entityToDto(Fertilizer fertilizer) {
    return new FertilizerDto(fertilizer.getId(), fertilizer.getName(), fertilizer.getBrand(),
        fertilizer.getComposition());
  }

  /**
   * Método para transformar dto em entidade.
   */
  public Fertilizer dtoToEntity() {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setName(this.name);
    fertilizer.setBrand(this.brand);
    fertilizer.setComposition(this.composition);
    return fertilizer;
  }
}
