package com.betrybe.agrix.solution;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PersonServiceTest {

  @MockBean
  private PersonRepository personRepository;
  @Autowired
  private PersonService personService;

  @Test
  public void testgetPersonByIdAndUsername() {
    Person personToReturn = new Person();
    personToReturn.setId(5L);
    personToReturn.setPassword("password123");
    personToReturn.setUsername("João");

    Mockito.when(personRepository.findById(eq(5L))).thenReturn(Optional.of(personToReturn));
    Mockito.when(personRepository.findByUsername("João")).thenReturn(Optional.of(personToReturn));

    Person returnedPersonById = personService.getPersonById(5L);
    Person returnedPersonByUsername = personService.getPersonByUsername("João");

    Mockito.verify(personRepository).findById(5L);
    Mockito.verify(personRepository).findByUsername("João");

    assertEquals(returnedPersonById.getId(), personToReturn.getId());
    assertEquals(returnedPersonById.getPassword(), personToReturn.getPassword());
    assertEquals(returnedPersonByUsername.getUsername(), personToReturn.getUsername());
  }

  @Test
  public void testCreatePerson() {
    Person personCreated = new Person();
    personCreated.setPassword("123");
    personCreated.setUsername("Paulo");

    Person personToReturn = new Person();
    personToReturn.setId(7L);
    personToReturn.setUsername(personCreated.getUsername());
    personToReturn.setPassword(personCreated.getPassword());

    Mockito.when(personRepository.save(any(Person.class))).thenReturn(personToReturn);

    Person savedPerson = personService.create(personCreated);

    Mockito.verify(personRepository).save(any(Person.class));

    assertEquals(7L, savedPerson.getId());
    assertEquals(personToReturn.getUsername(), savedPerson.getUsername());
    assertEquals(personToReturn.getPassword(), savedPerson.getPassword());
    assertEquals(personToReturn.getId(), savedPerson.getId());

  }

}
