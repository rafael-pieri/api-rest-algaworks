package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.dto.author.AuthorCreateDTO;
import com.algaworks.socialbooks.dto.author.AuthorDTO;
import com.algaworks.socialbooks.dto.author.AuthorPostObjectDTO;
import com.algaworks.socialbooks.dto.author.AuthorPutObjectDTO;
import com.algaworks.socialbooks.exceptions.AuthorNotFoundException;
import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.repository.AuthorRepository;
import com.algaworks.socialbooks.services.histories.AuthorHistoryService;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorHistoryService authorHistoryService;

    public AuthorService(final AuthorRepository authorRepository,
                         final AuthorHistoryService authorHistoryService) {
        this.authorRepository = authorRepository;
        this.authorHistoryService = authorHistoryService;
    }

    public Collection<AuthorDTO> findAll() {
        return this.authorRepository.findAll().stream()
                .map(author -> AuthorDTO.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .nationality(author.getNationality())
                        .build())
                .collect(Collectors.toList());
    }

    public AuthorDTO findById(final UUID id) {
        final Optional<Author> authorOptional = this.authorRepository.findById(id);

        if (authorOptional.isPresent()) {
            final Author author = authorOptional.get();
            return AuthorDTO.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .nationality(author.getNationality())
                    .build();
        }

        throw new AuthorNotFoundException("The author could not be found.");
    }

    public AuthorCreateDTO save(final AuthorPostObjectDTO authorPostObjectDTO) {
        final Author author = this.authorRepository.save(
                Author.builder()
                        .name(authorPostObjectDTO.getName())
                        .nationality(authorPostObjectDTO.getNationality())
                        .modifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                        .build()
        );

        this.authorHistoryService.createOrUpdate(author);

        return new AuthorCreateDTO(author.getId(), author.getModifiedAt());
    }

    public AuthorCreateDTO update(final UUID id,
                                  final AuthorPutObjectDTO authorPutObjectDTO) {
        final Optional<Author> authorOptional = this.authorRepository.findById(id);

        if (authorOptional.isPresent()) {
            final Author updatedAuthor = this.authorRepository.save(
                    Author.builder()
                            .id(id)
                            .name(authorPutObjectDTO.getName())
                            .nationality(authorPutObjectDTO.getNationality())
                            .modifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                            .build()
            );

            this.authorHistoryService.createOrUpdate(updatedAuthor);

            return new AuthorCreateDTO(updatedAuthor.getId(), updatedAuthor.getModifiedAt());
        }

        throw new AuthorNotFoundException(String.format("The author with id %s could not be found", id));
    }

    public void delete(final UUID id) {
        if (this.authorRepository.findById(id).isPresent()) {
            this.authorRepository.deleteById(id);
            this.authorHistoryService.softDeleteAuthorHistoryById(id);
        }

        throw new AuthorNotFoundException(String.format("The author with id %s could not be found", id));
    }
}