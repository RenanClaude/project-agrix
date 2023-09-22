package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Fertilizer Repository.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}
