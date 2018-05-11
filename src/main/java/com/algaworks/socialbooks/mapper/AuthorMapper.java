package com.algaworks.socialbooks.mapper;

import static com.algaworks.socialbooks.dto.AuthorDTO.AuthorBuilderDTO;

import com.algaworks.socialbooks.dto.AuthorDTO;
import com.algaworks.socialbooks.model.author.Author;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthorMapper {

    public static final AuthorMapper INSTANCE = new AuthorMapper();

    private AuthorMapper() {}

    public Collection<AuthorDTO> toAuthorsList(Optional<List<Author>> authors) {
        return authors.orElseGet(ArrayList::new).stream()
            .map((Author author) -> new AuthorBuilderDTO()
                .withId(author.getId())
                .withName(author.getName())
                .withBirth(author.getBirth())
                .withNationality(author.getNationality())
                .build())
            .collect(Collectors.toList());
    }
}
