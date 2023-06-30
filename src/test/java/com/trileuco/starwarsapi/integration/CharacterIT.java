package com.trileuco.starwarsapi.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trileuco.starwarsapi.dto.CharacterDTO;
import com.trileuco.starwarsapi.exception.ErrorDetails;
import com.trileuco.starwarsapi.model.Page;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CharacterIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private final static TypeReference<Page<CharacterDTO>> typeReference = new TypeReference<>() {};

    @Test
    void findCharactersWithoutParamsShouldReturnFirstNotEmptyPage() throws Exception {
        String url = "/person-info";
        MvcResult result = mockMvc.perform(get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Page<CharacterDTO> page = objectMapper.readValue(responseBody, typeReference);

        assertThat(page.getCount()).isGreaterThan(0);
        assertThat(page.getResults()).isNotEmpty();
    }

    @Test
    void findCharactersByNumPageShouldReturnSpecificNumPage() throws Exception {
        int numPage = 2;
        String url = String.format("/person-info?num_page=%d", numPage);

        MvcResult result = mockMvc.perform(get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Page<CharacterDTO> page = objectMapper.readValue(responseBody, typeReference);

        assertThat(page.getPrevious()).isEqualTo(numPage - 1);
        assertThat(page.getResults()).isNotEmpty();
    }

    @Test
    void findCharactersByNameShouldReturnCharactersWithThatName() throws Exception {
        String name = "Luke Skywalker";
        String url = String.format("/person-info?name=%s", name);

        MvcResult result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Page<CharacterDTO> page = objectMapper.readValue(responseBody, typeReference);

        assertThat(page.getCount()).isEqualTo(1);
        boolean containsLuke = page.getResults()
                .stream()
                .anyMatch(characterDTO -> characterDTO.getName().equals(name));
        assertTrue(containsLuke);
    }

    @Test
    void findCharactersShouldReturn404ResponseWhenPageNotExists() throws Exception {
        int numPage = 0;
        String url = String.format("/person-info?num_page=%d", numPage);

        MvcResult result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ErrorDetails details = objectMapper.readValue(responseBody, ErrorDetails.class);

        assertThat(details.getMessage()).isEqualTo(String.format("Character page number %d doesn't exist", numPage));
    }

    @Test
    void findCharactersShouldReturn404ResponseWhenCharacterNameNotExists() throws Exception {
        String name = "Muzska is Alive";
        String url = String.format("/person-info?name=%s", name);



        MvcResult result = mockMvc.perform(get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ErrorDetails details = objectMapper.readValue(responseBody, ErrorDetails.class);

        assertThat(details.getMessage()).isEqualTo(String.format("No characters found by name: %s", name));
    }

}
