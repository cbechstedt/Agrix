package com.betrybe.agrix.controllers;

import static io.micrometer.core.instrument.binder.http.HttpRequestTags.status;
import static org.junit.jupiter.api.Assertions.*;

import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FarmControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FarmService farmService;

  @Test
  void save() {
  }

  @Test
  void findById() {
  }

  @Test
  @WithMockUser(roles = {"USER", "MANAGER", "ADMIN"})
  void findAll() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/farms"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}