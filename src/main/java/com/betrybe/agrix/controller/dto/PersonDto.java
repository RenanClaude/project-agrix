package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.model.entities.Person;

/**
 * Person DTO.
 */
public record PersonDto(Long id, String username, Role role) {

}
