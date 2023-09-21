package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.PersonRequestDto;
import com.betrybe.agrix.dto.PersonResponseDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Define controlador para Person.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  /**
   * Define construtor.
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Requisição POST para criar person.
   */
  @PostMapping
  public ResponseEntity<PersonResponseDto> save(@RequestBody PersonRequestDto personRequestDto) {
    Person newPerson = personService.create(personRequestDto.dtoToEntity());
    PersonResponseDto newPersonResponseDto = PersonResponseDto.entityToDto(newPerson);
    return ResponseEntity.status(201).body(newPersonResponseDto);
  }

}
