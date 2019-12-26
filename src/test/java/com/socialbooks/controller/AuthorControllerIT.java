package com.socialbooks.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialbooks.dto.author.AuthorPostObjectDTO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
public class AuthorControllerIT extends AbstractIntegrationTest {

    private static final String API_AUTHORS = "/authors";
    private static final String NAME = "John";
    private static final String BRAZILIAN = "Brazilian";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @Test
    public void createANewAuthorShouldReturnCreate() throws Exception {
        this.mockMvc
                .perform(post(API_AUTHORS)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.objectMapper.writeValueAsString(getAuthorPostObjectDTO())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.lastModified").isNotEmpty())
                .andReturn();
    }

    private AuthorPostObjectDTO getAuthorPostObjectDTO() {
        return AuthorPostObjectDTO.builder()
                .name(NAME)
                .nationality(BRAZILIAN)
                .build();
    }
}