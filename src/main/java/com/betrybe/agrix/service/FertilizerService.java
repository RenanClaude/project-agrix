package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Fertilizer Service.
 */
@Service
public class FertilizerService {

  private FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return this.fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizers() {
    return this.fertilizerRepository.findAll();
  }

  public Optional<Fertilizer> getFertilizerById(Long id) {
    return this.fertilizerRepository.findById(id);
  }
}
