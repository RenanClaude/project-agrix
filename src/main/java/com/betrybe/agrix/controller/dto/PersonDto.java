package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.security.Role;

/**
 * Person DTO.
 */
public record PersonDto(Long id, String username, Role role) {

}
