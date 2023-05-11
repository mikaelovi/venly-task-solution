package com.venly.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.venly.task.entity.dto.RelationDto;
import com.venly.task.service.RelationService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RelationController.class)
@AutoConfigureMockMvc
class RelationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @MockBean
    private RelationService relationService;

    @BeforeEach
    public void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    void givenAllThreeEmptyParametersFailToCreateRelation() throws Exception {
        final var relationDto = new RelationDto("", "", "");

        mockMvc.perform(post("/relations/create")
                        .content(mapper.writeValueAsString(relationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.wordOne", Is.is("Word One may not be blank")))
                .andExpect(jsonPath("$.wordTwo", Is.is("Word Two may not be blank")))
                .andExpect(jsonPath("$.relationType", Is.is("Relation Type may not be blank")))
                .andReturn();
    }

    @Test
    void givenAllThreeNullParametersFailToCreateRelation() throws Exception {
        final var relationDto = new RelationDto(null, null, null);

        mockMvc.perform(post("/relations/create")
                        .content(mapper.writeValueAsString(relationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.wordOne", Is.is("Word One may not be blank")))
                .andExpect(jsonPath("$.wordTwo", Is.is("Word Two may not be blank")))
                .andExpect(jsonPath("$.relationType", Is.is("Relation Type may not be blank")))
                .andReturn();
    }


    @Test
    void givenOneOrMoreEmptyParametersFailToCreateRelation() throws Exception {
        final var relationDto = new RelationDto("word", "", "");

        mockMvc.perform(post("/relations/create")
                        .content(mapper.writeValueAsString(relationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.wordTwo", Is.is("Word Two may not be blank")))
                .andExpect(jsonPath("$.relationType", Is.is("Relation Type may not be blank")))
                .andReturn();
    }

    @Test
    void givenOneOrMoreNullParametersFailToCreateRelation() throws Exception {
        final var relationDto = new RelationDto("word", null, "ANTONYM");

        mockMvc.perform(post("/relations/create")
                        .content(mapper.writeValueAsString(relationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.wordTwo", Is.is("Word Two may not be blank")))
                .andReturn();
    }

    @Test
    void givenOneOrMoreNullAndEmptyParametersFailToCreateRelation() throws Exception {
        final var relationDto = new RelationDto("", null, "ANTONYM");

        mockMvc.perform(post("/relations/create")
                        .content(mapper.writeValueAsString(relationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.wordOne", Is.is("Word One may not be blank")))
                .andExpect(jsonPath("$.wordTwo", Is.is("Word Two may not be blank")))
                .andReturn();
    }

    @Test
    void givenNonNullAndNonEmptyParametersThenCreateRelationAndReturnNonEmptyResponse() throws Exception {
        final var relationDto = new RelationDto("son", "daughter", "RELATED");

        final var result = mockMvc.perform(post("/relations/create")
                        .content(mapper.writeValueAsString(relationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //    final var resultContent = mapper.readValue(result.getResponse().getContentAsString(), RelationDto.class);

        assertTrue(result.getResponse().getContentAsString().length() > 0);
        /*assertEquals(relationDto.getWordOne(), resultContent.getWordOne());
        assertEquals(relationDto.getWordTwo(), resultContent.getWordTwo());
        assertEquals(relationDto.getRelationType(), resultContent.getRelationType());*/
    }

    @Test
    void givenNonNullAndNonEmptyParametersThenCreateRelationAndReturnExpectedResponse() throws Exception {
        final var relationDto = new RelationDto("son", "daughter", "RELATED");

        final var result = mockMvc.perform(post("/relations/create")
                        .content(mapper.writeValueAsString(relationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final var resultContent = mapper.readValue(result.getResponse().getContentAsString(), RelationDto.class);

        assertTrue(result.getResponse().getContentAsString().length() > 0);
        assertEquals(relationDto.getWordOne(), resultContent.getWordOne());
        assertEquals(relationDto.getWordTwo(), resultContent.getWordTwo());
        assertEquals(relationDto.getRelationType(), resultContent.getRelationType());
    }
}