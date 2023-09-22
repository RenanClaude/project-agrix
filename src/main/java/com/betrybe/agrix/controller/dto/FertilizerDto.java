package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.entities.Fertilizer;

/**
 * Fertilizer DTO.
 */
public record FertilizerDto(Fertilizer fertilizer) {

  /**
   * Method to return fertilizer DTO response.
   */
  public Fertilizer response() {
    Fertilizer fertilizerDto = new Fertilizer(
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
    fertilizerDto.setId(fertilizer.getId());
    fertilizerDto.setCrops(fertilizer.getCrops());
    return fertilizerDto;
  }
}
