package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.exception.CropNotFound;
import com.betrybe.agrix.exception.FarmNotFound;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Define camada controller para crop.
 */
@RestController
@RequestMapping()
public class CropController {

  private final CropService cropService;

  /**
   * Define construtor.
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Requisição POST para criar crop.
   */
  @PostMapping("/farms/{farmId}/crops")
  public ResponseEntity<CropDto> save(@RequestBody CropDto cropDto,
      @PathVariable("farmId") long farmId) throws FarmNotFound {
    Crop crop = cropService.save(cropDto.dtoToEntity(), farmId);
    CropDto response = CropDto.entityToDto(crop);
    return ResponseEntity.status(201).body(response);
  }

  /**
   * Requisição GET para buscar crops por farm.
   */
  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<List<CropDto>> findCropsByFarmId(@PathVariable("farmId") long farmId)
      throws FarmNotFound {
    List<Crop> cropList = cropService.findCropsByFarmId(farmId);
    List<CropDto> cropDtoList = cropList.stream().map(CropDto::entityToDto).toList();
    return ResponseEntity.status(200).body(cropDtoList);
  }

  /**
   * Requisição GET para buscar todas crops.
   */
  @GetMapping("/crops")
  public ResponseEntity<List<CropDto>> findAllCrops() {
    List<Crop> cropList = cropService.findAllCrops();
    List<CropDto> cropDtoList = cropList.stream().map(CropDto::entityToDto).toList();
    return ResponseEntity.status(200).body(cropDtoList);
  }

  /**
   * Requisição GET para buscar crop por id.
   */
  @GetMapping("/crops/{id}")
  public ResponseEntity<CropDto> findById(@PathVariable long id) throws CropNotFound {
    Crop crop = cropService.findById(id);
    CropDto cropDto = CropDto.entityToDto(crop);
    return ResponseEntity.status(200).body(cropDto);
  }

  /**
   * Requisição GET para buscar crops por data através de query string.
   */
  @GetMapping("/crops/search")
  public ResponseEntity<List<CropDto>> search(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<Crop> cropList = cropService.findCropsByHarvestDate(start, end);
    List<CropDto> cropDtoList = cropList.stream().map(CropDto::entityToDto).toList();
    return ResponseEntity.status(200).body(cropDtoList);
  }
}
