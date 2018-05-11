package com.algaworks.socialbooks.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.algaworks.socialbooks.dto.AuthorCreateDTO;
import com.algaworks.socialbooks.dto.AuthorDTO;
import com.algaworks.socialbooks.dto.AuthorPostObjectDTO;
import com.algaworks.socialbooks.services.AuthorsService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AuthorsService authorsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.context)
            .build();
    }

    @Test
    public void findAllAuthors() throws Exception {
        final UUID authorId = UUID.randomUUID();
        final Date birth = new Date();

        final AuthorDTO authorDTOToBeReturned = new AuthorDTO.AuthorBuilderDTO()
            .withId(authorId)
            .withName("Rafael")
            .withNationality("Brazilian")
            .withBirth(birth)
            .build();

        given(this.authorsService.list()).willReturn(Collections.singletonList(authorDTOToBeReturned));

        this
            .mockMvc
            .perform(get("/api/authors").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].id").value(authorId.toString()))
            .andExpect(jsonPath("$.[0].name").value("Rafael"))
            .andExpect(jsonPath("$.[0].nationality").value("Brazilian"))
//            .andExpect(jsonPath("$.[0].birth").value(birth.toString()))
            .andReturn();
    }

    @Test
    public void findAuthorById() throws Exception {
        final UUID authorId = UUID.randomUUID();
        final Date birth = new Date();

        final AuthorDTO authorDTOToBeReturned = new AuthorDTO.AuthorBuilderDTO()
            .withId(authorId)
            .withName("Rafael")
            .withNationality("Brazilian")
            .withBirth(birth)
            .build();

        given(this.authorsService.findById(authorId)).willReturn(authorDTOToBeReturned);

        this
            .mockMvc
            .perform(get("/api/authors/" + authorId).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(authorId.toString()))
            .andExpect(jsonPath("$.name").value("Rafael"))
            .andExpect(jsonPath("$.nationality").value("Brazilian"))
//            .andExpect(jsonPath("$.[0].birth").value(birth.toString()))
            .andReturn();
    }

    @Test
    public void createANewAuthor() throws Exception {
        final UUID authorId = UUID.randomUUID();
        final Date birth = new Date();

        final AuthorCreateDTO authorCreateDTO = new AuthorCreateDTO(authorId, ZonedDateTime.now());

        final AuthorPostObjectDTO authorPostObjectDTO = new AuthorPostObjectDTO();
        authorPostObjectDTO.setName("Rafael");
        authorPostObjectDTO.setBirth(new Date());
        authorPostObjectDTO.setNationality("Brazilian");

        given(this.authorsService.save(authorPostObjectDTO)).willReturn(authorCreateDTO);

        MvcResult mvcResult = this
            .mockMvc
            .perform(post("/api/authors")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorPostObjectDTO)))
            .andExpect(status().isCreated())
//            .andExpect(jsonPath("$.id").value(authorId.toString()))
//            .andExpect(jsonPath("$.[0].name").value("Rafael"))
//            .andExpect(jsonPath("$.[0].nationality").value("Brazilian"))
//            .andExpect(jsonPath("$.[0].birth").value(birth.toString()))
            .andReturn();

        System.out.println("AuthorControllerIT.createANewAuthor: " + mvcResult.getResponse().getContentAsString());
    }
}
