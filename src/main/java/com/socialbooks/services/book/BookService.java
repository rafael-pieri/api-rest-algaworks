package com.socialbooks.services.book;

import com.socialbooks.dto.author.AuthorDTO;
import com.socialbooks.dto.book.BookCreateDTO;
import com.socialbooks.dto.book.BookDTO;
import com.socialbooks.dto.book.BookPostObjectDTO;
import com.socialbooks.dto.book.BookPutObjectDTO;
import com.socialbooks.exceptions.BookNotFoundException;
import com.socialbooks.model.author.Author;
import com.socialbooks.model.book.Book;
import com.socialbooks.repository.BookRepository;
import com.socialbooks.services.author.AuthorService;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookHistoryService bookHistoryService;
    private final AuthorService authorService;

    public BookService(final BookRepository bookRepository,
                       final BookHistoryService bookHistoryService,
                       final AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookHistoryService = bookHistoryService;
        this.authorService = authorService;
    }

    public Book getBook(final UUID id) {
        final Optional<Book> optionalBook = this.bookRepository.findById(id);

        if (optionalBook.isPresent())
            return optionalBook.get();

        throw new BookNotFoundException(String.format("The book with id %s could not be found.", id));
    }

    public Collection<BookDTO> list() {
        return this.bookRepository.findAll().stream()
                .map(book -> BookDTO.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .publication(book.getPublication())
                        .publisher(book.getPublisher())
                        .summary(book.getSummary())
                        .author(AuthorDTO.builder()
                                .id(book.getAuthor().getId())
                                .name(book.getAuthor().getName())
                                .nationality(book.getAuthor().getNationality())
                                .build())
                        .comments(book.getComments())
                        .build())
                .collect(Collectors.toList());
    }

    public BookDTO findById(final UUID id) {
        final Book book = getBook(id);

        return BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .publication(book.getPublication())
                .publisher(book.getPublisher())
                .summary(book.getSummary())
                .author(AuthorDTO.builder()
                        .id(book.getAuthor().getId())
                        .name(book.getAuthor().getName())
                        .nationality(book.getAuthor().getNationality())
                        .build())
                .comments(book.getComments())
                .build();
    }

    public BookCreateDTO save(final BookPostObjectDTO bookPostObjectDTO) {
        final Author author = this.authorService.getAuthor(bookPostObjectDTO.getAuthorId());

        final Book createdBook = this.bookRepository.save(
                Book.builder()
                        .name(bookPostObjectDTO.getName())
                        .publication(bookPostObjectDTO.getPublication())
                        .publisher(bookPostObjectDTO.getPublisher())
                        .summary(bookPostObjectDTO.getSummary())
                        .author(author)
                        .modifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                        .build()
        );

        this.bookHistoryService.createOrUpdate(createdBook);

        return new BookCreateDTO(createdBook.getId(), createdBook.getModifiedAt());
    }

    public BookCreateDTO update(final UUID id, final BookPutObjectDTO bookPutObjectDTO) {
        final Book book = getBook(id);
        final Author author = this.authorService.getAuthor(bookPutObjectDTO.getAuthorId());

        final Book updatedBook = this.bookRepository.save(
                Book.builder()
                        .id(book.getId())
                        .name(bookPutObjectDTO.getName())
                        .publication(bookPutObjectDTO.getPublication())
                        .publisher(bookPutObjectDTO.getPublisher())
                        .summary(bookPutObjectDTO.getSummary())
                        .author(author)
                        .modifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                        .build()
        );

        this.bookHistoryService.createOrUpdate(updatedBook);

        return new BookCreateDTO(updatedBook.getId(), updatedBook.getModifiedAt());
    }

    public void delete(final UUID id) {
        final Book book = getBook(id);
        this.bookRepository.deleteById(book.getId());
        this.bookHistoryService.softDeleteBookHistoryById(book.getId());
    }
}