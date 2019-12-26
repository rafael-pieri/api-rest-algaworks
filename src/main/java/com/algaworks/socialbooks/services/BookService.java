package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.dto.author.AuthorDTO;
import com.algaworks.socialbooks.dto.book.BookCreateDTO;
import com.algaworks.socialbooks.dto.book.BookDTO;
import com.algaworks.socialbooks.dto.book.BookPostObjectDTO;
import com.algaworks.socialbooks.dto.book.BookPutObjectDTO;
import com.algaworks.socialbooks.exceptions.AuthorNotFoundException;
import com.algaworks.socialbooks.exceptions.BookNotFoundException;
import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.model.book.Book;
import com.algaworks.socialbooks.model.book.BookHistory;
import com.algaworks.socialbooks.repository.AuthorRepository;
import com.algaworks.socialbooks.repository.BookRepository;
import com.algaworks.socialbooks.services.histories.BookHistoryService;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        final List<Book> books = bookRepository.findAll();

        final ArrayList<BookDTO> bookDTOS = new ArrayList<>();

        books.forEach(book -> {
            final AuthorDTO authorDTO = AuthorDTO.builder()
                    .id(book.getAuthor().getId())
                    .name(book.getAuthor().getName())
                    .nationality(book.getAuthor().getNationality())
                    .build();

            final BookDTO bookDTO = BookDTO.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .publication(book.getPublication())
                    .publisher(book.getPublisher())
                    .summary(book.getSummary())
                    .author(authorDTO)
                    .comments(book.getComments())
                    .build();

            bookDTOS.add(bookDTO);
        });

        return bookDTOS;
    }

    public BookDTO findById(final UUID id) {
        final Optional<Book> bookOptional = bookRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new BookNotFoundException(String.format("The book with id %s could not be found.", id));
        }

        final Book book = bookOptional.get();

        final AuthorDTO authorDTO = AuthorDTO.builder()
                .id(book.getAuthor().getId())
                .name(book.getAuthor().getName())
                .nationality(book.getAuthor().getNationality())
                .build();

        return BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .publication(book.getPublication())
                .publisher(book.getPublisher())
                .summary(book.getSummary())
                .author(authorDTO)
                .comments(book.getComments())
                .build();
    }

    public BookCreateDTO save(final BookPostObjectDTO bookPostObjectDTO) {
        final Optional<Author> author = authorRepository.findById(bookPostObjectDTO.getAuthorId());

        if (!author.isPresent()) {
            throw new AuthorNotFoundException(String.format("The author with id %s could not be found", bookPostObjectDTO.getAuthorId()));
        }

        final Book book = new Book.BookBuilder()
                .withName(bookPostObjectDTO.getName())
                .withPublication(bookPostObjectDTO.getPublication())
                .withPublisher(bookPostObjectDTO.getPublisher())
                .withSummary(bookPostObjectDTO.getSummary())
                .withAuthor(author.get())
                .withModifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .build();

        final Book createdBook = bookRepository.save(book);

        final BookHistory bookHistory = new BookHistory.BookHistoryBuilder()
                .withName(createdBook.getName())
                .withPublication(createdBook.getPublication())
                .withPublisher(createdBook.getPublisher())
                .withSummary(createdBook.getSummary())
                .withAuthor(createdBook.getAuthor())
                .withCreatedAt(createdBook.getModifiedAt())
                .build();

        bookHistoryService.createOrUpdate(bookHistory);

        return new BookCreateDTO(createdBook.getId(), createdBook.getModifiedAt());
    }

    public BookCreateDTO update(final UUID id, final BookPutObjectDTO bookPutObjectDTO) {
        final Optional<Book> bookOptional = bookRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new BookNotFoundException(String.format("The book with id %s could not be found", id));
        }

        final Optional<Author> author = authorRepository.findById(bookPutObjectDTO.getAuthorId());

        if (!author.isPresent()) {
            throw new AuthorNotFoundException(String.format("The author with id %s could not be found", bookPutObjectDTO.getAuthorId()));
        }

        final Book book = new Book.BookBuilder()
                .withId(id)
                .withName(bookPutObjectDTO.getName())
                .withPublication(bookPutObjectDTO.getPublication())
                .withPublisher(bookPutObjectDTO.getPublisher())
                .withSummary(bookPutObjectDTO.getSummary())
                .withAuthor(author.get())
                .withModifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .build();

        final Book updatedBook = bookRepository.save(book);

        final BookHistory bookHistory = new BookHistory.BookHistoryBuilder()
                .withName(updatedBook.getName())
                .withPublication(updatedBook.getPublication())
                .withPublisher(updatedBook.getPublisher())
                .withSummary(updatedBook.getSummary())
                .withAuthor(updatedBook.getAuthor())
                .withCreatedAt(updatedBook.getModifiedAt())
                .build();

        bookHistoryService.createOrUpdate(bookHistory);

        return new BookCreateDTO(updatedBook.getId(), updatedBook.getModifiedAt());

    }

    public void delete(final UUID id) {
        if (!bookRepository.findById(id).isPresent()) {
            throw new BookNotFoundException(String.format("The book with id %s could not be found.", id));
        }

        bookRepository.deleteById(id);

        softDeleteBookHistoryById(id);
    }

    private void softDeleteBookHistoryById(final UUID id) {
        final Optional<BookHistory> bookHistoryOptional = bookHistoryService.findTopByBookIdOrderByCreatedAtDesc(id);

        if (bookHistoryOptional.isPresent()) {
            final BookHistory bookHistory = bookHistoryOptional.get();

            final BookHistory updatedBookHistory = new BookHistory.BookHistoryBuilder()
                    .withId(bookHistory.getId())
                    .withName(bookHistory.getName())
                    .withPublication(bookHistory.getPublication())
                    .withPublisher(bookHistory.getPublisher())
                    .withSummary(bookHistory.getSummary())
                    .withAuthor(bookHistory.getAuthor())
                    .withCreatedAt(bookHistory.getCreatedAt())
                    .withDeletedAt(ZonedDateTime.now(ZoneOffset.UTC))
                    .build();

            bookHistoryService.createOrUpdate(updatedBookHistory);
        }
    }
}