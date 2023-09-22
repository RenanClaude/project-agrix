package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Crop;

/**
 * Crop DTO.
 */
public record CropDto(Crop crop) {

  /**
   * Method to return crop DTO response.
   */
  public Crop response() {
    Crop cropDto = new Crop(crop.getName(), crop.getPlantedArea());
    cropDto.setId(crop.getId());
    cropDto.setFarmId(crop.getFarm().getId());
    cropDto.setPlantedDate(crop.getPlantedDate());
    cropDto.setHarvestDate(crop.getHarvestDate());
    cropDto.setFertilizers(crop.getFertilizers());
    return cropDto;
  }
}
