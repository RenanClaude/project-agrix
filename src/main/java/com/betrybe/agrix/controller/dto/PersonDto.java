package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Person;

/**
 * Person DTO.
 */
public record PersonDto(Person person) {

  /**
   * Method to return a person DTO response.
   */
  public Person response() {
    Person personDto = new Person();
    personDto.setId(person.getId());
    personDto.setUsername(person.getUsername());
    personDto.setRole(person.getRole());

    return personDto;
  }
}
