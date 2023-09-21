package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * Define DTO.
 */
public record PersonRequestDto(long id, String username, String password, Role role) {

  /**
   * Método para transformar dto em entidade.
   */
  public Person dtoToEntity() {
    Person person = new Person();
    person.setUsername(this.username);
    person.setPassword(this.password);
    person.setRole(this.role);
    return person;
  }
}
