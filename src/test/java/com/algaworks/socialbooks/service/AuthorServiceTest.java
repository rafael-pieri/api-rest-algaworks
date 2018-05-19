package com.algaworks.socialbooks.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.algaworks.socialbooks.dto.AuthorPostObjectDTO;
import com.algaworks.socialbooks.exceptions.AuthorNotFoundException;
import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.repository.AuthorRepository;
import com.algaworks.socialbooks.services.AuthorHistoryService;
import com.algaworks.socialbooks.services.AuthorService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    private static final String NAME = "Name";
    private static final String BRAZILIAN = "Brazilian";

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorHistoryService authorHistoryService;

    @Captor
    private ArgumentCaptor<Author> authorArgumentCaptor;

    @Test
    public void shouldFindAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        authorService.findAll();

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindAuthorById() {
        final UUID authorId = UUID.randomUUID();

        when(authorRepository.findOne(authorId)).thenReturn(Optional.of(new Author()));

        authorService.findById(authorId);

        verify(authorRepository, times(1)).findOne(authorId);
    }

    @Test(expected = AuthorNotFoundException.class)
    public void shouldRaiseAuthorNotFoundException() {
        final UUID authorId = UUID.randomUUID();

        when(authorRepository.findOne(authorId)).thenThrow(new AuthorNotFoundException());

        authorService.findById(authorId);
    }

    @Test
    public void shouldCreateANewAuthor() {
        final Date birth = Date.from(ZonedDateTime.of(LocalDate.of(1974, 1, 15).atStartOfDay(), ZoneId.systemDefault
            ()).toInstant());

        final AuthorPostObjectDTO authorPostObjectDTO = new AuthorPostObjectDTO.AuthorPostObjectBuilderDTO()
            .withName(NAME)
            .withBirth(birth)
            .withNationality(BRAZILIAN)
            .build();

        when(authorRepository.save(authorArgumentCaptor.capture())).thenReturn(Optional.of(new Author()));
        doNothing().when(authorHistoryService).save(authorArgumentCaptor.capture());

        authorService.save(authorPostObjectDTO);

        verify(authorRepository, times(1)).save(authorArgumentCaptor.capture());
        verify(authorHistoryService, times(1)).save(authorArgumentCaptor.capture());
    }
}