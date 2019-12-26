package com.socialbooks.repository;

import com.socialbooks.model.author.AuthorHistory;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorHistoryRepository extends JpaRepository<AuthorHistory, UUID> {

    Optional<AuthorHistory> findTopByAuthorIdOrderByCreatedAtDesc(UUID authorId);
}