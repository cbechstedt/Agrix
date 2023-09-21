package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.exception.CropNotFound;
import com.betrybe.agrix.exception.FertilizerNotFound;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Define camada controller de Fertilizer.
 */
@RestController
@RequestMapping()
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Define construtor.
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Requisição POST para criar fertilizer.
   */
  @PostMapping("/fertilizers")
  public ResponseEntity<FertilizerDto> save(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer fertilizer = fertilizerService.save(fertilizerDto.dtoToEntity());
    FertilizerDto responde = FertilizerDto.entityToDto(fertilizer);
    return ResponseEntity.status(201).body(responde);
  }

  /**
   * Requisição GET para buscar todos fertilizers.
   */
  @GetMapping("/fertilizers")
  @Secured("ADMIN")
  public ResponseEntity<List<FertilizerDto>> findAll() {
    List<Fertilizer> fertilizerList = fertilizerService.findAll();
    List<FertilizerDto> fertilizerDtoList = fertilizerList.stream().map(FertilizerDto::entityToDto)
        .toList();
    return ResponseEntity.status(200).body(fertilizerDtoList);
  }

  /**
   * Requisição GET para buscar fertilizer por id.
   */
  @GetMapping("/fertilizers/{id}")
  public ResponseEntity<FertilizerDto> findById(@PathVariable long id) {
    Fertilizer fertilizer = fertilizerService.findById(id);
    FertilizerDto fertilizerDto = FertilizerDto.entityToDto(fertilizer);
    return ResponseEntity.status(200).body(fertilizerDto);
  }

  /**
   * Requisição POST para associar uma crop com um fertilizer.
   */
  @PostMapping("/crops/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropWithFertilizer(
      @PathVariable long cropId,
      @PathVariable long fertilizerId) {
    try {
      fertilizerService.associateCropWithFertilizer(cropId, fertilizerId);
      return ResponseEntity.status(201)
          .body("Fertilizante e plantação associados com sucesso!");
    } catch (CropNotFound e) {
      return ResponseEntity.status(404)
          .body("Plantação não encontrada!");
    } catch (FertilizerNotFound e) {
      return ResponseEntity.status(404)
          .body("Fertilizante não encontrado!");
    }
  }


}
