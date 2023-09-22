package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Farm;

/**
 * Farm DTO.
 */
public record FarmDto(Farm farm) {

  /**
   * Method to return farm DTO response.
   */
  public Farm response() {
    Farm farmDto = new Farm(farm.getName(), farm.getSize());
    farmDto.setId(farm.getId());
    return farmDto;
  }
}
