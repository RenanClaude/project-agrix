package com.betrybe.agrix.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity Crop.
 */
@Entity
@Table(name = "crops")
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double plantedArea;
  @ManyToOne
  @JoinColumn(name = "farm")
  @JsonInclude(Include.NON_NULL)
  private Farm farm;
  private Long farmId;
  private LocalDate plantedDate;
  private LocalDate harvestDate;
  @ManyToMany
  @JoinTable(
      name = "crops_fertilizers",
      joinColumns = @JoinColumn(name = "fertilizer_id"),
      inverseJoinColumns = @JoinColumn(name = "crop_id")
  )
  @JsonInclude(Include.NON_EMPTY)
  private List<Fertilizer> fertilizers;

  public Crop(String name, Double plantedArea) {
    this.name = name;
    this.plantedArea = plantedArea;
  }

  /**
   * Crop constructor.
   */
  public Crop(String name, Double plantedArea, Farm farm) {
    this.name = name;
    this.plantedArea = plantedArea;
    this.farm = farm;
    this.farmId = farm.getId();
  }

  public Crop() {
  }

  public Long getFarmId() {
    return farmId;
  }

  public void setFarmId(Long farmId) {
    this.farmId = farmId;
  }

  public Farm getFarm() {
    return farm;
  }

  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPlantedArea() {
    return plantedArea;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }

  public void addFertilizer(Fertilizer newFertilizer) {
    this.fertilizers.add(newFertilizer);
  }
}
