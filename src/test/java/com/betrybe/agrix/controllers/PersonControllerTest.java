package com.betrybe.agrix.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.agrix.dto.PersonRequestDto;
import com.betrybe.agrix.dto.PersonResponseDto;
import com.betrybe.agrix.security.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Should return status created and the correct object")
  void save() throws Exception {
    PersonRequestDto personRequestDto = new PersonRequestDto("username", "test", Role.ADMIN);

    ObjectMapper objectMapper = new ObjectMapper();

    String personJson = objectMapper.writeValueAsString(personRequestDto);

    MvcResult mvcResult = mockMvc.perform(post("/persons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(personJson))
        .andExpect(status().isCreated())
        .andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    PersonResponseDto personResponseDto = objectMapper.readValue(content, PersonResponseDto.class);

    assertNotNull(personResponseDto.id());
    assertEquals("username", personResponseDto.username());
    assertEquals(Role.ADMIN, personResponseDto.role());


  }
}