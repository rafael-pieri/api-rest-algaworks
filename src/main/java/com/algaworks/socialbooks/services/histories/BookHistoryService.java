package com.algaworks.socialbooks.services.histories;

import com.algaworks.socialbooks.model.book.BookHistory;
import com.algaworks.socialbooks.repository.BookHistoryRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class BookHistoryService {

    private final BookHistoryRepository bookHistoryRepository;

    public BookHistoryService(final BookHistoryRepository bookHistoryRepository) {
        this.bookHistoryRepository = bookHistoryRepository;
    }

    public void createOrUpdate(final BookHistory bookHistory) {
        bookHistoryRepository.save(bookHistory);
    }

    public Optional<BookHistory> findTopByBookIdOrderByCreatedAtDesc(final UUID bookId) {
        return bookHistoryRepository.findTopByAuthorIdOrderByCreatedAtDesc(bookId);
    }
}