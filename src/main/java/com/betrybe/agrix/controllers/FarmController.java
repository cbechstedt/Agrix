package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.exception.FarmNotFound;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Define controlador para Farm.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Define construtor.
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Requisição POST para criar farm.
   */
  @PostMapping
  public ResponseEntity<FarmDto> save(@RequestBody FarmDto farmDto) {
    Farm farm = farmService.save(farmDto.dtoToEntity());
    FarmDto response = FarmDto.entityToDto(farm);
    return ResponseEntity.status(201)
        .body(response);
  }

  /**
   * Requisição GET para listar todas farms.
   */
  @GetMapping
  @Secured({"USER", "MANAGER", "ADMIN"})
  public ResponseEntity<List<FarmDto>> findAll() {
    List<Farm> farmList = farmService.findAll();
    List<FarmDto> farmDtoList = farmList.stream().map(FarmDto::entityToDto).toList();
    return ResponseEntity.status(200).body(farmDtoList);
  }

  /**
   * Requisição GET para buscar 'farm' por id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> findById(@PathVariable("id") long id) throws FarmNotFound {
    Farm farm = farmService.findById(id);
    FarmDto farmDto = FarmDto.entityToDto(farm);
    return ResponseEntity.status(200).body(farmDto);
  }

  /**
   * Javadoc.
   */
  @PutMapping("/{id}")
  public ResponseEntity<FarmDto> update(@PathVariable("id") long id,
      @RequestBody FarmDto farmDto) throws FarmNotFound {
    Farm farm = farmService.findById(id);
    farm.setName(farmDto.name());
    farm.setSize(farmDto.size());

    Farm updatedFarm = farmService.save(farm);
    FarmDto updatedFarmDto = FarmDto.entityToDto(updatedFarm);
    return ResponseEntity.status(200).body(updatedFarmDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") long id) {
    farmService.delete(id);
    return ResponseEntity.status(204).build();
  }

}
