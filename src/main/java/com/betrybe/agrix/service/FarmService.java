package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.model.repositories.FarmRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Farm Service.
 */
@Service
public class FarmService {

  private FarmRepository farmRepository;
  private CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm createFarm(Farm farm) {
    return this.farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  public Optional<Farm> getFarmById(Long id) {
    return farmRepository.findById(id);
  }

  /**
   * Method in the service to create a crop for a farm.
   */
  public Crop createCrop(Crop crop, Long farmId) {
    Optional<Farm> farm = farmRepository.findById(farmId);
    if (farm.isEmpty()) {
      throw new FarmNotFoundException();
    }
    crop.setFarm(farm.get());
    return this.cropRepository.save(crop);
  }

  /**
   * Method to get the list of crops on a farm.
   */
  public List<Crop> getCropsByFarmId(Long farmId) {
    Optional<Farm> farm = farmRepository.findById(farmId);
    if (farm.isEmpty()) {
      throw new FarmNotFoundException();
    }
    return farm.get().getCrops();
  }
}
