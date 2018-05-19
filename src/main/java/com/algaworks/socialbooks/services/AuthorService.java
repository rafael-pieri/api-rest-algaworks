package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.dto.AuthorCreateDTO;
import com.algaworks.socialbooks.dto.AuthorDTO;
import com.algaworks.socialbooks.dto.AuthorPostObjectDTO;
import com.algaworks.socialbooks.exceptions.AuthorNotFoundException;
import com.algaworks.socialbooks.mapper.AuthorMapper;
import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.repository.AuthorRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorHistoryService authorHistoryService;

    public Collection<AuthorDTO> findAll() {
        final Optional<List<Author>> authors = authorRepository.findAll();

        return AuthorMapper.INSTANCE.toAuthorsList(authors);
    }

    public AuthorDTO findById(UUID id) {
        final Optional<Author> author = authorRepository.findOne(id);

        if (author.isPresent()) {
            final Author selectedAuthor = author.get();

            return new AuthorDTO.AuthorBuilderDTO()
                .withId(selectedAuthor.getId())
                .withName(selectedAuthor.getName())
                .withBirth(selectedAuthor.getBirth())
                .withNationality(selectedAuthor.getNationality())
                .build();
        }

        throw new AuthorNotFoundException("The author could not be found.");
    }

    public AuthorCreateDTO save(AuthorPostObjectDTO authorPostObjectDTO) {
        final Author author = new Author.AuthorBuilder()
            .withName(authorPostObjectDTO.getName())
            .withBirth(authorPostObjectDTO.getBirth())
            .withNationality(authorPostObjectDTO.getNationality())
            .build();

        final Author createdAuthor = authorRepository.save(author).orElseThrow(RuntimeException::new);

        authorHistoryService.save(createdAuthor);

        return new AuthorCreateDTO(createdAuthor.getId(), createdAuthor.getModifiedAt());
    }
}