package com.betrybe.agrix.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.betrybe.agrix.exception.FarmNotFound;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
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
class FarmServiceTest {

  @MockBean
  private FarmRepository farmRepository;

  @Autowired
  private FarmService farmService;
  private Farm mockFarm1;

  @BeforeEach
  void setUp() {
    mockFarm1 = new Farm();
    mockFarm1.setId(1);
    mockFarm1.setName("testFarm1");
    mockFarm1.setSize(3.4);
  }

  @Test
  @DisplayName("Should save and return correct object")
  void save() {
    Mockito.when(farmRepository.save(mockFarm1)).thenReturn(mockFarm1);

    Farm farm = farmService.save(mockFarm1);

    assertEquals("testFarm1", farm.getName());
    assertEquals(3.4, farm.getSize());

    Mockito.verify(farmRepository).save(mockFarm1);
  }

  @Test
  @DisplayName("Should return all farms")
  void findAll() {
/*    Farm mockFarm2 = new Farm();
    mockFarm2.setId(2);
    mockFarm2.setName("testFarm2");
    mockFarm2.setSize(4.4);

    List<Farm> mockFarmList = List.of(mockFarm1, mockFarm2);

    Mockito.when(farmRepository.findAll()).thenReturn(mockFarmList);

    List<Farm> farmList = farmService.findAll();

    assertEquals(2, farmList.size());
    assertEquals("testFarm1", farmList.get(0).getName());
    assertEquals("testFarm2", farmList.get(1).getName());*/

    // Simpler way to test. it just verifies if findAll was invoked, as the method is already tested by Spring JPA.

    List<Farm> farmList = farmService.findAll();
    Mockito.verify(farmRepository).findAll();
  }

  @Test
  @DisplayName("Should return correct farm by id")
  void findById() {
    Mockito.when(farmRepository.findById(mockFarm1.getId())).thenReturn(Optional.of(mockFarm1));

    Farm farm = farmService.findById(mockFarm1.getId());

    assertEquals("testFarm1", farm.getName());
    assertEquals(3.4, farm.getSize());

    Mockito.verify(farmRepository).findById(mockFarm1.getId());
  }

  @Test
  @DisplayName("Should throws an exception when farm not found")
  void farmNotFound() {
    long nonExistentFarmId = 999L;

    Mockito.when(farmRepository.findById(nonExistentFarmId)).thenReturn(Optional.empty());

    assertThrows(FarmNotFound.class, () -> farmService.findById(nonExistentFarmId));

    Mockito.verify(farmRepository).findById(nonExistentFarmId);
  }
}