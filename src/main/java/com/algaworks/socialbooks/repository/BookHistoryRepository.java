package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.model.book.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookHistoryRepository extends JpaRepository<BookHistory, UUID> {

    Optional<BookHistory> findTopByAuthorIdOrderByCreatedAtDesc(UUID bookId);
}