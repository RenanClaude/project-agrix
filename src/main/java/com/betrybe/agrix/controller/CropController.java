package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crops controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Route to get all crops.
   */
  @GetMapping
  @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})

  public ResponseEntity<List<Crop>> getAllCrops() {
    List<Crop> allCrops = cropService.getAllCrops();
    List<Crop> cropsDto = allCrops.stream()
        .map((crop) -> new CropDto(crop).response())
        .collect(Collectors.toList());
    return ResponseEntity.ok().body(cropsDto);
  }

  /**
   * Route to get a crop by id.
   */
  @GetMapping("/{id}")
  @ResponseBody
  public Crop getCropById(@PathVariable Long id) {
    Optional<Crop> crop = cropService.getCropById(id);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    CropDto cropDto = new CropDto(crop.get());
    return cropDto.response();
  }

  @GetMapping("/search")
  public List<Crop> getCropsByDateRange(@RequestParam String start, @RequestParam String end) {
    List<Crop> crops = cropService.getCropsByDateRange(start, end);
    return crops.stream().map((crop) -> new CropDto(crop).response()).collect(Collectors.toList());
  }

  /**
   * Route to associate crop and fertilizer.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropAndFertilizer(
      @PathVariable Long cropId, @PathVariable Long fertilizerId) {

    String successfullyAssociated = this
        .cropService.associateCropAndFertilizer(cropId, fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED).body(successfullyAssociated);
  }

  /**
   * Route to get fertilizers from a crop.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<Fertilizer>> getFertilizersFromCrop(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = this.cropService.getFertilizersFromCrop(cropId);
    List<Fertilizer> fertilizersFromEntity =
        fertilizers.stream().map((fertilizer) -> new FertilizerDto(fertilizer).response()).toList();

    return ResponseEntity.status(HttpStatus.OK).body(fertilizers);
  }
}
