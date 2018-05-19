package com.algaworks.socialbooks.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.algaworks.socialbooks.dto.AuthorCreateDTO;
import com.algaworks.socialbooks.dto.AuthorDTO;
import com.algaworks.socialbooks.dto.AuthorPostObjectDTO;
import com.algaworks.socialbooks.exceptions.AuthorNotFoundException;
import com.algaworks.socialbooks.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorControllerIT {

    private static final String API_AUTHORS = "/api/authors/";
    private static final String NAME = "Name";
    private static final String BRAZILIAN = "Brazilian";
    private static final String THE_AUTHOR_COULD_NOT_BE_FOUND = "The author could not be found.";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.context)
            .build();
    }

    @Test
    public void findAllAuthorsShouldReturnOk() throws Exception {
        final UUID authorId = UUID.randomUUID();
        final Date birth = Date.from(ZonedDateTime.of(LocalDate.of(1974, 1, 15).atStartOfDay(), ZoneId.systemDefault
            ()).toInstant());

        final AuthorDTO authorDTOToBeReturned = new AuthorDTO.AuthorBuilderDTO()
            .withId(authorId)
            .withName(NAME)
            .withBirth(birth)
            .withNationality(BRAZILIAN)
            .build();

        given(this.authorService.findAll()).willReturn(Collections.singletonList(authorDTOToBeReturned));

        this.mockMvc
            .perform(get("/api/authors").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].id").value(authorId.toString()))
            .andExpect(jsonPath("$.[0].name").value(NAME))
            .andExpect(jsonPath("$.[0].birth").value(simpleDateFormat.format(birth)))
            .andExpect(jsonPath("$.[0].nationality").value(BRAZILIAN))
            .andReturn();
    }

    @Test
    public void findAuthorByIdShouldReturnOk() throws Exception {
        final UUID authorId = UUID.randomUUID();
        final Date birth = Date.from(ZonedDateTime.of(LocalDate.of(1974, 1, 15).atStartOfDay(), ZoneId.systemDefault
            ()).toInstant());

        final AuthorDTO authorDTOToBeReturned = new AuthorDTO.AuthorBuilderDTO()
            .withId(authorId)
            .withName(NAME)
            .withBirth(birth)
            .withNationality(BRAZILIAN)
            .build();

        given(this.authorService.findById(authorId)).willReturn(authorDTOToBeReturned);

        this.mockMvc
            .perform(get(API_AUTHORS.concat(authorId.toString())).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(authorId.toString()))
            .andExpect(jsonPath("$.name").value(NAME))
            .andExpect(jsonPath("$.birth").value(simpleDateFormat.format(birth)))
            .andExpect(jsonPath("$.nationality").value(BRAZILIAN))
            .andReturn();
    }

    @Test
    public void findAuthorByIdShouldRaiseAuthorNotFoundException() throws Exception {
        final UUID authorId = UUID.randomUUID();

        given(this.authorService.findById(authorId))
            .willThrow(new AuthorNotFoundException(THE_AUTHOR_COULD_NOT_BE_FOUND));

        this.mockMvc
            .perform(get(API_AUTHORS.concat(authorId.toString())).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.title").value(THE_AUTHOR_COULD_NOT_BE_FOUND))
            .andExpect(jsonPath("$.status").value(404))
            .andReturn();
    }

    @Test
    public void createANewAuthorShouldReturnCreate() throws Exception {
        final UUID authorId = UUID.randomUUID();
        final Date birth = Date.from(ZonedDateTime.of(LocalDate.of(1974, 1, 15).atStartOfDay(), ZoneId.systemDefault
            ()).toInstant());

        final AuthorCreateDTO authorCreateDTO = new AuthorCreateDTO(authorId, ZonedDateTime.now());

        final AuthorPostObjectDTO authorPostObjectDTO = new AuthorPostObjectDTO.AuthorPostObjectBuilderDTO()
            .withName(NAME)
            .withBirth(birth)
            .withNationality(BRAZILIAN)
            .build();

        given(this.authorService.save(any())).willReturn(authorCreateDTO);

        this.mockMvc
            .perform(post(API_AUTHORS)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorPostObjectDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(authorId.toString()))
            .andExpect(jsonPath("$.lastModified").isNotEmpty())
            .andReturn();
    }
}