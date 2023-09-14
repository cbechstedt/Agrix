package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.FarmNotFound;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Define service para Farm.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public Farm save(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  /**
   * Método para buscar farm por id.
   */
  public Farm findById(long id) {
    Optional<Farm> farm = farmRepository.findById(id);
    if (farm.isEmpty()) {
      throw new FarmNotFound("Fazenda não encontrada!");
    }
    return farm.get();
  }

}
