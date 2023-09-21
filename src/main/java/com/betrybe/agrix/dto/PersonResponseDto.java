package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * Define DTO.
 */
public record PersonResponseDto(long id, String username, Role role) {

  /**
   * Método para transformar entidade em dto.
   */
  public static PersonResponseDto entityToDto(Person person) {
    return new PersonResponseDto(person.getId(), person.getUsername(),
        person.getRole());
  }
}
