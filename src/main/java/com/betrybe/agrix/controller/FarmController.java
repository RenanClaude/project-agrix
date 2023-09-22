package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private FarmService farmService;

  @Autowired
  FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Route to create a farm (Post).
   */
  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
    Farm newFarm = farmService.createFarm(farm);
    FarmDto farmDto = new FarmDto(newFarm);
    return ResponseEntity.status(HttpStatus.CREATED).body(farmDto.response());
  }

  /**
   * Route to get all farms.
   */
  @GetMapping()
  public List<Farm> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    return allFarms.stream()
        .map((farm) -> new FarmDto(farm).response())
        .toList();
  }

  /**
   * Route to get a farm by id.
   */
  @GetMapping("/{id}")
  @ResponseBody
  public Farm getFarmById(@PathVariable Long id) {
    Optional<Farm> farm = farmService.getFarmById(id);
    if (farm.isEmpty()) {
      throw new FarmNotFoundException();
    }
    return farm.get();
  }

  /**
   * Route to create a crop on a specific farm.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<Crop> createCrop(@RequestBody Crop crop, @PathVariable Long farmId) {
    Crop newCrop = farmService.createCrop(crop, farmId);
    CropDto cropDto = new CropDto(newCrop);
    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto.response());
  }

  /**
   * Route to get all crops by farm id.
   */
  @GetMapping("/{farmId}/crops")
  public List<Crop> getCropsByFarmId(@PathVariable Long farmId) {
    List<Crop> crops = farmService.getCropsByFarmId(farmId);
    return crops.stream()
        .map((crop) -> new CropDto(crop).response())
        .collect(Collectors.toList());
  }
}
