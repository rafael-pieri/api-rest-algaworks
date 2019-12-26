package com.socialbooks.services;

import com.socialbooks.dto.author.AuthorDTO;
import com.socialbooks.dto.book.BookCreateDTO;
import com.socialbooks.dto.book.BookDTO;
import com.socialbooks.dto.book.BookPostObjectDTO;
import com.socialbooks.dto.book.BookPutObjectDTO;
import com.socialbooks.exceptions.AuthorNotFoundException;
import com.socialbooks.exceptions.BookNotFoundException;
import com.socialbooks.model.author.Author;
import com.socialbooks.model.book.Book;
import com.socialbooks.repository.AuthorRepository;
import com.socialbooks.repository.BookRepository;
import com.socialbooks.services.histories.BookHistoryService;

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
    private final AuthorRepository authorRepository;

    public BookService(final BookRepository bookRepository,
                       final BookHistoryService bookHistoryService,
                       final AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookHistoryService = bookHistoryService;
        this.authorRepository = authorRepository;
    }

    public Collection<BookDTO> list() {
        return this.bookRepository.findAll().stream()
                .map(book -> BookDTO.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .publication(book.getPublication())
                        .publisher(book.getPublisher())
                        .summary(book.getSummary())
                        .author(
                                AuthorDTO.builder()
                                        .id(book.getAuthor().getId())
                                        .name(book.getAuthor().getName())
                                        .nationality(book.getAuthor().getNationality())
                                        .build()
                        )
                        .comments(book.getComments())
                        .build())
                .collect(Collectors.toList());
    }

    public BookDTO findById(final UUID id) {
        final Optional<Book> bookOptional = this.bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            final Book book = bookOptional.get();

            return BookDTO.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .publication(book.getPublication())
                    .publisher(book.getPublisher())
                    .summary(book.getSummary())
                    .author(
                            AuthorDTO.builder()
                                    .id(book.getAuthor().getId())
                                    .name(book.getAuthor().getName())
                                    .nationality(book.getAuthor().getNationality())
                                    .build()
                    )
                    .comments(book.getComments())
                    .build();
        }

        throw new BookNotFoundException(String.format("The book with id %s could not be found.", id));
    }

    public BookCreateDTO save(final BookPostObjectDTO bookPostObjectDTO) {
        final Optional<Author> author = this.authorRepository.findById(bookPostObjectDTO.getAuthorId());

        if (author.isPresent()) {
            final Book createdBook = this.bookRepository.save(
                    Book.builder()
                            .name(bookPostObjectDTO.getName())
                            .publication(bookPostObjectDTO.getPublication())
                            .publisher(bookPostObjectDTO.getPublisher())
                            .summary(bookPostObjectDTO.getSummary())
                            .author(author.get())
                            .modifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                            .build()
            );

            this.bookHistoryService.createOrUpdate(createdBook);

            return new BookCreateDTO(createdBook.getId(), createdBook.getModifiedAt());
        }

        throw new AuthorNotFoundException(String.format("The author with id %s could not be found", bookPostObjectDTO.getAuthorId()));
    }

    public BookCreateDTO update(final UUID id, final BookPutObjectDTO bookPutObjectDTO) {
        final Optional<Book> bookOptional = this.bookRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new BookNotFoundException(String.format("The book with id %s could not be found", id));
        }

        final Optional<Author> author = this.authorRepository.findById(bookPutObjectDTO.getAuthorId());

        if (!author.isPresent()) {
            throw new AuthorNotFoundException(String.format("The author with id %s could not be found", bookPutObjectDTO.getAuthorId()));
        }

        final Book updatedBook = this.bookRepository.save(
                Book.builder()
                        .id(id)
                        .name(bookPutObjectDTO.getName())
                        .publication(bookPutObjectDTO.getPublication())
                        .publisher(bookPutObjectDTO.getPublisher())
                        .summary(bookPutObjectDTO.getSummary())
                        .author(author.get())
                        .modifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                        .build()
        );

        this.bookHistoryService.createOrUpdate(updatedBook);

        return new BookCreateDTO(updatedBook.getId(), updatedBook.getModifiedAt());
    }

    public void delete(final UUID id) {
        if (!this.bookRepository.findById(id).isPresent()) {
            throw new BookNotFoundException(String.format("The book with id %s could not be found.", id));
        }

        this.bookRepository.deleteById(id);
        this.bookHistoryService.softDeleteBookHistoryById(id);
    }
}