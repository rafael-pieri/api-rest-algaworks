package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.model.book.BookHistory;
import com.algaworks.socialbooks.repository.BookHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookHistoryService {

    private final BookHistoryRepository bookHistoryRepository;

    @Autowired
    public BookHistoryService(final BookHistoryRepository bookHistoryRepository) {
        this.bookHistoryRepository = bookHistoryRepository;
    }

    void createOrUpdate(final BookHistory bookHistory) {
        bookHistoryRepository.save(bookHistory);
    }

    Optional<BookHistory> findTopByBookIdOrderByCreatedAtDesc(final UUID bookId) {
        return bookHistoryRepository.findTopByAuthorIdOrderByCreatedAtDesc(bookId);
    }
}