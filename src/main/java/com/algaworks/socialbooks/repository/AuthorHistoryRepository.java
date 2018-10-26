package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.model.author.AuthorHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorHistoryRepository extends JpaRepository<AuthorHistory, UUID> {

    Optional<AuthorHistory> findTopByAuthorIdOrderByCreatedAtDesc(UUID authorId);
}