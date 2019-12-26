package com.socialbooks.repository;

import com.socialbooks.model.book.BookHistory;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookHistoryRepository extends JpaRepository<BookHistory, UUID> {

    Optional<BookHistory> findTopByBookIdOrderByCreatedAtDesc(UUID bookId);
}