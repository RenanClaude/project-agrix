package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Crop repository.
 */
public interface CropRepository extends JpaRepository<Crop, Long> {

  public List<Crop> findAllByharvestDateBetween(LocalDate startDate, LocalDate endDate);
}
