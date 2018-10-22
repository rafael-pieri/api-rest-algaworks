package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.dto.author.AuthorDTO;
import com.algaworks.socialbooks.dto.book.BookDTO;
import com.algaworks.socialbooks.dto.book.BookPostObjectDTO;
import com.algaworks.socialbooks.dto.book.BookUpdateDTO;
import com.algaworks.socialbooks.exceptions.AuthorNotFoundException;
import com.algaworks.socialbooks.exceptions.BookNotFoundException;
import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.model.book.Book;
import com.algaworks.socialbooks.repository.AuthorRepository;
import com.algaworks.socialbooks.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookHistoryService bookHistoryService;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(final BookRepository bookRepository,
                       final BookHistoryService bookHistoryService,
                       final AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookHistoryService = bookHistoryService;
        this.authorRepository = authorRepository;
    }

    public Collection<BookDTO> list() {
        ArrayList<BookDTO> bookDTO = new ArrayList<>();

        BeanUtils.copyProperties(bookRepository.findAll(), bookDTO);

        return bookDTO;
    }

    public BookDTO findById(final UUID id) {
        final Optional<Book> book = bookRepository.findById(id);

        if (!book.isPresent()) {
            throw new BookNotFoundException(String.format("The book with id %s could not be found.", id));
        }

        BookDTO bookDTO = new BookDTO();

        BeanUtils.copyProperties(book.get(), bookDTO);

        return bookDTO;
    }

    public BookDTO save(final BookPostObjectDTO bookPostObjectDTO) {
        Optional<Author> author = authorRepository.findById(bookPostObjectDTO.getAuthorId());

        if (!author.isPresent()) {
            throw new AuthorNotFoundException(String.format("The author with id %s could not be found", bookPostObjectDTO.getAuthorId()));
        }

        final Book book = new Book.BookBuilder()
                .withName(bookPostObjectDTO.getName())
                .withPublication(bookPostObjectDTO.getPublication())
                .withPublisher(bookPostObjectDTO.getPublisher())
                .withSummary(bookPostObjectDTO.getSummary())
                .withAuthor(author.get())
                .build();

        Book save = bookRepository.save(book);

        AuthorDTO authorDTO = new AuthorDTO.AuthorBuilderDTO()
                .withId(author.get().getId())
                .withName(author.get().getName())
                .withNationality(author.get().getNationality())
                .build();

        BookDTO bookDTO = new BookDTO(save.getId(), save.getName(), save.getPublication(), save.getPublisher(), save.getSummary(),
                save.getComments(), authorDTO);

//        BeanUtils.copyProperties(bookRepository.createOrUpdate(book), bookDTO);

        return bookDTO;
    }

    public void delete(final UUID id) {
        if (!bookRepository.findById(id).isPresent()) {
            throw new BookNotFoundException(String.format("The book with id %s could not be found.", id));
        }

        bookRepository.deleteById(id);
    }

    public void update(final Long id, final BookUpdateDTO bookUpdateDTO) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        final Book book = new Book.BookBuilder()
                .withId(id)
                .withName(bookUpdateDTO.getName())
                .withPublication(bookUpdateDTO.getPublication())
                .withPublisher(bookUpdateDTO.getPublisher())
                .withSummary(bookUpdateDTO.getSummary())
                .withComments(bookUpdateDTO.getComments())
                .withAuthor(bookUpdateDTO.getAuthor())
                .build();

        bookRepository.save(book);
    }
}