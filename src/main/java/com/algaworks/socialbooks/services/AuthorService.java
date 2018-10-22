package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.dto.author.AuthorCreateDTO;
import com.algaworks.socialbooks.dto.author.AuthorDTO;
import com.algaworks.socialbooks.dto.author.AuthorPostObjectDTO;
import com.algaworks.socialbooks.dto.author.AuthorPutObjectDTO;
import com.algaworks.socialbooks.exceptions.AuthorNotFoundException;
import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.model.author.AuthorHistory;
import com.algaworks.socialbooks.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorHistoryService authorHistoryService;

    @Autowired
    public AuthorService(final AuthorRepository authorRepository,
                         final AuthorHistoryService authorHistoryService) {
        this.authorRepository = authorRepository;
        this.authorHistoryService = authorHistoryService;
    }

    public Collection<Author> findAll() {
        return new ArrayList<>(authorRepository.findAll());
    }

    public AuthorDTO findById(final UUID id) {
        final Optional<Author> author = authorRepository.findById(id);

        if (!author.isPresent()) {
            throw new AuthorNotFoundException("The author could not be found.");
        }

        final Author selectedAuthor = author.get();

        return new AuthorDTO.AuthorBuilderDTO()
                .withId(selectedAuthor.getId())
                .withName(selectedAuthor.getName())
                .withNationality(selectedAuthor.getNationality())
                .build();
    }

    public AuthorCreateDTO save(final AuthorPostObjectDTO authorPostObjectDTO) {
        final Author author = new Author.AuthorBuilder()
                .withName(authorPostObjectDTO.getName())
                .withNationality(authorPostObjectDTO.getNationality())
                .withModifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .build();

        final Author createdAuthor = authorRepository.save(author);

        final AuthorHistory authorHistory = new AuthorHistory.AuthorHistoryBuilder()
                .withAuthorId(author.getId())
                .withName(author.getName())
                .withNationality(author.getNationality())
                .withCreatedAt(author.getModifiedAt())
                .build();

        authorHistoryService.createOrUpdate(authorHistory);

        return new AuthorCreateDTO(createdAuthor.getId(), createdAuthor.getModifiedAt());
    }

    public AuthorCreateDTO update(final UUID id, final AuthorPutObjectDTO authorPutObjectDTO) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if (!optionalAuthor.isPresent()) {
            throw new AuthorNotFoundException(String.format("The author with id %s could not be found", id));
        }

        final Author author = new Author.AuthorBuilder()
                .withId(id)
                .withName(authorPutObjectDTO.getName())
                .withNationality(authorPutObjectDTO.getNationality())
                .withModifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .build();

        final Author createdAuthor = authorRepository.save(author);

        final AuthorHistory authorHistory = new AuthorHistory.AuthorHistoryBuilder()
                .withAuthorId(author.getId())
                .withName(author.getName())
                .withNationality(author.getNationality())
                .withCreatedAt(author.getModifiedAt())
                .build();

        authorHistoryService.createOrUpdate(authorHistory);

        return new AuthorCreateDTO(createdAuthor.getId(), createdAuthor.getModifiedAt());
    }

    public void delete(final UUID id) {
        if (!authorRepository.findById(id).isPresent()) {
            throw new AuthorNotFoundException(String.format("The author with id %s could not be found", id));
        }

        authorRepository.deleteById(id);

        softDeleteNotificationHistoryByNotificationId(id);
    }

    private void softDeleteNotificationHistoryByNotificationId(final UUID id) {
        Optional<AuthorHistory> optionalAuthorHistory = authorHistoryService.findTopByAuthorIdOrderByCreatedAtDesc(id);

        if (optionalAuthorHistory.isPresent()) {
            final AuthorHistory authorHistory = optionalAuthorHistory.get();

            final AuthorHistory updatedAuthorHistory = new AuthorHistory.AuthorHistoryBuilder()
                    .withId(authorHistory.getId())
                    .withAuthorId(authorHistory.getAuthorId())
                    .withName(authorHistory.getName())
                    .withNationality(authorHistory.getNationality())
                    .withCreatedAt(authorHistory.getCreatedAt())
                    .withDeletedAt(ZonedDateTime.now(ZoneOffset.UTC))
                    .build();

            authorHistoryService.createOrUpdate(updatedAuthorHistory);
        }
    }
}