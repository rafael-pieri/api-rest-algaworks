package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.model.author.AuthorHistory;
import com.algaworks.socialbooks.repository.AuthorHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorHistoryService {

    private final AuthorHistoryRepository authorHistoryRepository;

    @Autowired
    public AuthorHistoryService(final AuthorHistoryRepository authorHistoryRepository) {
        this.authorHistoryRepository = authorHistoryRepository;
    }

    void createOrUpdate(final AuthorHistory authorHistory) {
        authorHistoryRepository.save(authorHistory);
    }

    Optional<AuthorHistory> findTopByAuthorIdOrderByCreatedAtDesc(final UUID authorId) {
        return authorHistoryRepository.findTopByAuthorIdOrderByCreatedAtDesc(authorId);
    }
}