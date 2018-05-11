package com.algaworks.socialbooks.repository;

import java.util.List;
import java.util.Optional;

import com.algaworks.socialbooks.model.author.Author;
import java.util.UUID;
import org.springframework.data.repository.Repository;

public interface AuthorsRepository extends Repository<Author, UUID> {

    Optional<Author> findOne(UUID id);

    Optional<List<Author>> findAll();

    Optional<Author> save(Author author);
}