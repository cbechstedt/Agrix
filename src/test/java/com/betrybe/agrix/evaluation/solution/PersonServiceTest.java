package com.betrybe.agrix.evaluation.solution;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.exception.PersonNotFoundException;
import com.betrybe.agrix.models.repositories.PersonRepository;
import com.betrybe.agrix.security.Role;
import com.betrybe.agrix.services.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTest {

  @MockBean
  PersonRepository personRepository;

  @Autowired
  PersonService personService;

  @Test
  @DisplayName("Deve retornar objeto Person correto quando id existente for passado")
  public void testGetPersonById() {
    Person mockPerson = new Person();
    mockPerson.setId(1L);
    mockPerson.setUsername("Christian");
    mockPerson.setPassword("123456");
    mockPerson.setRole(Role.ADMIN);

    Mockito.when(personRepository.findById(mockPerson.getId()))
        .thenReturn(Optional.of(mockPerson));

    Long id = 1L;
    Person person = personService.getPersonById(id);

    Assertions.assertNotNull(person);
    Assertions.assertEquals(id, person.getId());
    Assertions.assertEquals("Christian", person.getUsername());
    Assertions.assertEquals("123456", person.getPassword());
    Assertions.assertEquals(Role.ADMIN, person.getRole());

    Mockito.verify(personRepository).findById(id);
  }

  @Test
  @DisplayName("Deve retornar objeto Person correto quando username existente for passado")
  public void testGetPersonByUsername() {
    Person mockPerson = new Person();
    mockPerson.setId(1L);
    mockPerson.setUsername("Christian");
    mockPerson.setPassword("123456");
    mockPerson.setRole(Role.ADMIN);

    Mockito.when(personRepository.findByUsername(mockPerson.getUsername()))
        .thenReturn(Optional.of(mockPerson));

    String username = "Christian";
    Person person = personService.getPersonByUsername(username);

    Assertions.assertNotNull(person);
    Assertions.assertEquals(1L, person.getId());
    Assertions.assertEquals("Christian", person.getUsername());
    Assertions.assertEquals("123456", person.getPassword());
    Assertions.assertEquals(Role.ADMIN, person.getRole());

    Mockito.verify(personRepository).findByUsername(username);
  }

  @Test
  @DisplayName("Deve criar e retornar um objeto Person correto quando um novo Person for salvo")
  public void testCreatePerson() {
    Person mockPerson = new Person();
    mockPerson.setId(1L);
    mockPerson.setUsername("Christian");
    mockPerson.setPassword("123456");
    mockPerson.setRole(Role.ADMIN);

    Mockito.when(personRepository.save(mockPerson)).thenReturn(mockPerson);

    Long id = 1L;
    Person person = personService.create(mockPerson);

    Assertions.assertNotNull(person);
    Assertions.assertEquals(id, person.getId());
    Assertions.assertEquals("Christian", person.getUsername());
    Assertions.assertEquals("123456", person.getPassword());
    Assertions.assertEquals(Role.ADMIN, person.getRole());

    Mockito.verify(personRepository).save(person);
  }

  @Test
  @DisplayName("Deve lançar exceção quando Person não existir")
  public void testPersonNotFound() {
    Mockito.when(personRepository.findById(any())).thenReturn(Optional.empty());

    Long id = 999999999L;

    Assertions.assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(id));

    Mockito.verify(personRepository).findById(eq(id));
  }
}
