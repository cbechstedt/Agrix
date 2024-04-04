package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * Define DTO.
 */
public record PersonRequestDto(String username, String password, Role role) {

  /**
   * Javadoc.
   */
  public PersonRequestDto {
    if (password == null || password.length() < 6) {
      throw new IllegalArgumentException("Password must be at least 6 characters long");
    }
  }

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
