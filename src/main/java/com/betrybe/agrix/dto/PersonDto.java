package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * Define DTO.
 */
public record PersonDto(long id, String username, Role role) {

  /**
   * Método para transformar entidade em dto.
   */
  public static PersonDto entityToDto(Person person) {
    return new PersonDto(person.getId(), person.getUsername(),
        person.getRole());
  }

  /**
   * Método para transformar dto em entidade.
   */
  public Person dtoToEntity() {
    Person person = new Person();
    person.setUsername(this.username);
    person.setRole(this.role);
    return person;
  }
}
