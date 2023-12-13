package com.betrybe.agrix.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.betrybe.agrix.exception.CropNotFound;
import com.betrybe.agrix.exception.FarmNotFound;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CropServiceTest {

  @MockBean
  private CropRepository cropRepository;

  @MockBean
  private FarmRepository farmRepository;

  @Autowired
  private CropService cropService;

  private Farm mockFarm;
  private Crop mockCrop;

  @BeforeEach
  void setUp() {
    mockFarm = new Farm();
    mockFarm.setId(1);
    mockFarm.setName("Carola");
    mockFarm.setSize(2.2);

    mockCrop = new Crop();
    mockCrop.setId(1);
    mockCrop.setName("Rice");
    mockCrop.setPlantedArea(5.2);
    mockCrop.setPlantedDate(LocalDate.parse("2022-01-01"));
    mockCrop.setHarvestDate(LocalDate.parse("2023-01-01"));
    mockCrop.setFarm(mockFarm);

  }

  @Test
  @DisplayName("Should save and return correct Crop object")
  void save() {
    Farm mockfarm = new Farm();
    mockfarm.setId(1L);

    Mockito.when(farmRepository.findById(1L)).thenReturn(Optional.of(mockfarm));
    Mockito.when(cropRepository.save(mockCrop)).thenReturn(mockCrop);

    Crop crop = cropService.save(mockCrop, mockfarm.getId());

    assertEquals("Rice", crop.getName());

    Mockito.verify(farmRepository).findById(1L);
    Mockito.verify(cropRepository).save(mockCrop);
  }

  @Test
  @DisplayName("Should throws farm not found exception when farm is not found")
  void saveReturnFarmNotFound() {
    long nonExistentFarmId = 999L;

    Mockito.when(farmRepository.findById(nonExistentFarmId)).thenReturn(Optional.empty());

    assertThrows(FarmNotFound.class, () -> cropService.save(mockCrop, nonExistentFarmId));

    Mockito.verify(farmRepository).findById(nonExistentFarmId);
  }

//  @Test
//  @DisplayName("Should save and return correct Crop object")
//  void testSaveWhenCropSavedThenReturnCrop() {
//    Crop mockCrop = new Crop();
//    Farm mockFarm = new Farm();
//    mockFarm.setId(1L);
//    Mockito.when(farmRepository.findById(1L)).thenReturn(Optional.of(mockFarm));
//    Mockito.when(cropRepository.save(mockCrop)).thenReturn(mockCrop);
//
//    Crop savedCrop = cropService.save(mockCrop, 1L);
//
//    assertEquals(mockCrop, savedCrop);
//  }

//  @Test
//  @DisplayName("Test 'save' method when Farm is not found")
//  void testSaveWhenFarmNotFoundThenThrowException() {
//    Crop mockCrop = new Crop();
//    Mockito.when(farmRepository.findById(1L)).thenReturn(Optional.empty());
//
//    assertThrows(FarmNotFound.class, () -> cropService.save(mockCrop, 1L));
//  }

  @Test
  void findCropsByFarmId() {

  }

  @Test
  void findAllCrops() {
    cropService.findAllCrops();

    Mockito.verify(cropRepository).findAll();
  }

  @Test
  void findById() {
    // TODO: Implement this test
  }

  @Test
  void findCropsByHarvestDate() {
    // TODO: Implement this test
  }


}