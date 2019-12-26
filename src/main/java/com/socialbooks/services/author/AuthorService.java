package com.socialbooks.services.author;

import static java.lang.String.format;

import com.socialbooks.dto.author.AuthorCreateDTO;
import com.socialbooks.dto.author.AuthorDTO;
import com.socialbooks.dto.author.AuthorPostObjectDTO;
import com.socialbooks.dto.author.AuthorPutObjectDTO;
import com.socialbooks.exceptions.AuthorNotFoundException;
import com.socialbooks.model.author.Author;
import com.socialbooks.repository.AuthorRepository;

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

    public Author getAuthor(UUID id) {
        final Optional<Author> optionalAuthor = this.authorRepository.findById(id);

        if (optionalAuthor.isPresent())
            return optionalAuthor.get();

        throw new AuthorNotFoundException(format("The author %s with id could not be found.", id));
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
        final Author author = getAuthor(id);
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .nationality(author.getNationality())
                .build();
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
        final Author author = getAuthor(id);

        final Author updatedAuthor = this.authorRepository.save(
                Author.builder()
                        .id(author.getId())
                        .name(authorPutObjectDTO.getName())
                        .nationality(authorPutObjectDTO.getNationality())
                        .modifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                        .build()
        );

        this.authorHistoryService.createOrUpdate(updatedAuthor);

        return new AuthorCreateDTO(updatedAuthor.getId(), updatedAuthor.getModifiedAt());
    }

    public void delete(final UUID id) {
        final Author author = getAuthor(id);
        this.authorRepository.deleteById(author.getId());
        this.authorHistoryService.softDeleteAuthorHistoryById(author.getId());
    }
}