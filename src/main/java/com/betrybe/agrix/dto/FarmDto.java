package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * Define DTO.
 */
public record FarmDto(long id, String name, double size) {

  /**
   * Método para transformar entidade em dto.
   */
  public static FarmDto entityToDto(Farm farm) {
    return new FarmDto(farm.getId(), farm.getName(), farm.getSize());
  }

  /**
   * Método para transformar dto em entidade.
   */
  public Farm dtoToEntity() {
    Farm farm = new Farm();
    farm.setName(this.name);
    farm.setSize(this.size);
    return farm;
  }
}
