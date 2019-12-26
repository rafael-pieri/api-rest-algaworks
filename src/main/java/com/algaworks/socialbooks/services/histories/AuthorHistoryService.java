package com.algaworks.socialbooks.services.histories;

import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.model.author.AuthorHistory;
import com.algaworks.socialbooks.repository.AuthorHistoryRepository;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class AuthorHistoryService {

    private final AuthorHistoryRepository authorHistoryRepository;

    public AuthorHistoryService(final AuthorHistoryRepository authorHistoryRepository) {
        this.authorHistoryRepository = authorHistoryRepository;
    }

    public void createOrUpdate(final Author author) {
        this.authorHistoryRepository.save(
                AuthorHistory.builder()
                        .authorId(author.getId())
                        .name(author.getName())
                        .nationality(author.getNationality())
                        .createdAt(author.getModifiedAt())
                        .build()
        );
    }

    public void softDeleteAuthorHistoryById(final UUID id) {
        findTopByAuthorIdOrderByCreatedAtDesc(id).ifPresent(authorHistory ->
                this.authorHistoryRepository.save(AuthorHistory.builder()
                        .id(authorHistory.getId())
                        .authorId(authorHistory.getAuthorId())
                        .name(authorHistory.getName())
                        .nationality(authorHistory.getNationality())
                        .createdAt(authorHistory.getCreatedAt())
                        .deletedAt(ZonedDateTime.now(ZoneOffset.UTC))
                        .build()
                ));
    }

    private Optional<AuthorHistory> findTopByAuthorIdOrderByCreatedAtDesc(final UUID authorId) {
        return this.authorHistoryRepository.findTopByAuthorIdOrderByCreatedAtDesc(authorId);
    }
}