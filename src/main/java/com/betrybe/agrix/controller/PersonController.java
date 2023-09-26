package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.PersonDto;
import com.betrybe.agrix.model.entities.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Persons Controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Route to create a person.
   */
  @PostMapping
  public ResponseEntity<PersonDto> createPerson(@RequestBody Person newPerson) {
    Person personCreated = this.personService.create(newPerson);

    PersonDto personFromEntity = new PersonDto(
        personCreated.getId(),
        personCreated.getUsername(),
        personCreated.getRole());

    return ResponseEntity.status(HttpStatus.CREATED).body(personFromEntity);
  }
}
