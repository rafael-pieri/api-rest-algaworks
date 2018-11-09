package com.algaworks.socialbooks.service;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

//    private static final String NAME = "Name";
//    private static final String BRAZILIAN = "Brazilian";
//
//    @InjectMocks
//    private AuthorService authorService;
//
//    @Mock
//    private AuthorRepository authorRepository;
//
//    @Mock
//    private AuthorHistoryService authorHistoryService;
//
//    @Captor
//    private ArgumentCaptor<Author> authorArgumentCaptor;
//
//    @Test
//    public void shouldFindAllAuthors() {
//        when(authorRepository.findAll()).thenReturn(Optional.of(new ArrayList<>()));
//
//        authorService.findAll();
//
//        verify(authorRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void shouldFindAuthorById() {
//        final UUID authorId = UUID.randomUUID();
//
//        when(authorRepository.findById(authorId)).thenReturn(Optional.of(new Author()));
//
//        authorService.findById(authorId);
//
//        verify(authorRepository, times(1)).findById(authorId);
//    }
//
//    @Test(expected = AuthorNotFoundException.class)
//    public void shouldRaiseAuthorNotFoundException() {
//        final UUID authorId = UUID.randomUUID();
//
//        when(authorRepository.findById(authorId)).thenThrow(new AuthorNotFoundException());
//
//        authorService.findById(authorId);
//    }
//
//    @Test
//    public void shouldCreateANewAuthor() {
//        final Date birth = Date.from(ZonedDateTime.of(LocalDate.of(1974, 1, 15).atStartOfDay(), ZoneId.systemDefault
//            ()).toInstant());
//
//        final AuthorPostObjectDTO authorPostObjectDTO = new AuthorPostObjectDTO.AuthorPostObjectBuilderDTO()
//            .withName(NAME)
////            .withBirth(birth)
//            .withNationality(BRAZILIAN)
//            .build();
//
//        when(authorRepository.save(authorArgumentCaptor.capture())).thenReturn(Optional.of(new Author()));
//        doNothing().when(authorHistoryService).save(authorArgumentCaptor.capture());
//
//        authorService.save(authorPostObjectDTO);
//
//        verify(authorRepository, times(1)).save(authorArgumentCaptor.capture());
//        verify(authorHistoryService, times(1)).save(authorArgumentCaptor.capture());
//    }
}