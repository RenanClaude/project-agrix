package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fertilizer Controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private FertilizerService fertilizerService;

  /**
   * Constructor of Fertilizer Controller.
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Route to create a fertilizer (Post).
   */
  @PostMapping()
  public ResponseEntity<Fertilizer> createFertilizer(@RequestBody Fertilizer fertilizer) {
    Fertilizer newFertilizer = this.fertilizerService.createFertilizer(fertilizer);
    FertilizerDto fertilizerDto = new FertilizerDto(newFertilizer);
    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerDto.response());
  }

  /**
   * Route to get all fertilizers.
   */
  @GetMapping()
  @Secured("ROLE_ADMIN")

  public ResponseEntity<List<Fertilizer>> getAllFertilizers() {
    List<Fertilizer> fertilizers = this.fertilizerService.getAllFertilizers();

    List<Fertilizer> fertilizersFromEntity = fertilizers.stream()
        .map((fertilizer) -> new FertilizerDto(fertilizer).response()).toList();

    return ResponseEntity.status(HttpStatus.OK).body(fertilizersFromEntity);
  }

  /**
   * Route to get a fertilizer by id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Fertilizer> getFertilizerById(@PathVariable Long id) {
    Optional<Fertilizer> fertilizer = this.fertilizerService.getFertilizerById(id);
    if (fertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }
    return ResponseEntity.status(HttpStatus.OK).body(fertilizer.get());
  }
}
