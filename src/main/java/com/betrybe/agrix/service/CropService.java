package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Optional<Crop> getCropById(Long id) {
    return cropRepository.findById(id);
  }

  /**
   * Method to return crops with harvest date within a time range.
   */
  public List<Crop> getCropsByDateRange(String start, String end) {
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);
    return cropRepository.findAllByharvestDateBetween(startDate, endDate);
  }

  /**
   * Method in service to associate crop and fertilizer.
   */
  public String associateCropAndFertilizer(Long cropId, Long fertilizerId) {
    Optional<Crop> crop = this.cropRepository.findById(cropId);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    Optional<Fertilizer> fertilizer = this.fertilizerRepository.findById(fertilizerId);
    if (fertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }
    crop.get().addFertilizer(fertilizer.get());
    this.cropRepository.save(crop.get());
    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Méthod to get all fertilizers from a crop.
   */
  public List<Fertilizer> getFertilizersFromCrop(Long cropId) {
    Optional<Crop> crop = this.cropRepository.findById(cropId);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    return crop.get().getFertilizers();
  }
}
