package com.socialbooks.repository;

import com.socialbooks.model.book.BookHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookHistoryRepository extends JpaRepository<BookHistory, UUID> {

    Optional<BookHistory> findTopByBookIdOrderByCreatedAtDesc(UUID bookId);
}