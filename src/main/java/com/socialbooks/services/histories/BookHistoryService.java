package com.socialbooks.services.histories;

import com.socialbooks.model.book.Book;
import com.socialbooks.model.book.BookHistory;
import com.socialbooks.repository.BookHistoryRepository;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class BookHistoryService {

    private final BookHistoryRepository bookHistoryRepository;

    public BookHistoryService(final BookHistoryRepository bookHistoryRepository) {
        this.bookHistoryRepository = bookHistoryRepository;
    }

    public void createOrUpdate(final Book book) {
        this.bookHistoryRepository.save(
                BookHistory.builder()
                        .bookId(book.getId())
                        .name(book.getName())
                        .publication(book.getPublication())
                        .publisher(book.getPublisher())
                        .summary(book.getSummary())
                        .author(book.getAuthor())
                        .createdAt(book.getModifiedAt())
                        .build()
        );
    }

    public void softDeleteBookHistoryById(final UUID id) {
        final Optional<BookHistory> bookHistoryOptional = findTopByBookIdOrderByCreatedAtDesc(id);

        if (bookHistoryOptional.isPresent()) {
            final BookHistory bookHistory = bookHistoryOptional.get();
            final BookHistory updatedBookHistory = BookHistory.builder()
                    .id(bookHistory.getId())
                    .bookId(bookHistory.getBookId())
                    .name(bookHistory.getName())
                    .publication(bookHistory.getPublication())
                    .publisher(bookHistory.getPublisher())
                    .summary(bookHistory.getSummary())
                    .author(bookHistory.getAuthor())
                    .createdAt(bookHistory.getCreatedAt())
                    .deletedAt(ZonedDateTime.now(ZoneOffset.UTC))
                    .build();
            this.bookHistoryRepository.save(updatedBookHistory);
        }
    }

    private Optional<BookHistory> findTopByBookIdOrderByCreatedAtDesc(final UUID bookId) {
        return bookHistoryRepository.findTopByBookIdOrderByCreatedAtDesc(bookId);
    }
}