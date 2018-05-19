package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.model.author.AuthorHistory;
import com.algaworks.socialbooks.repository.AuthorHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorHistoryService {

    @Autowired
    private AuthorHistoryRepository authorHistoryRepository;

    public void save(Author author) {
        final AuthorHistory authorHistory = new AuthorHistory.AuthorHistoryBuilder()
            .withAuthorId(author.getId())
            .withName(author.getName())
            .withBirth(author.getBirth())
            .withNationality(author.getNationality())
            .build();

        authorHistoryRepository.save(authorHistory);
    }
}