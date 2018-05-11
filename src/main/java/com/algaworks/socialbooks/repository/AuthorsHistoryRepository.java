package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.model.author.AuthorHistory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.Repository;

public interface AuthorsHistoryRepository extends Repository<AuthorHistory, UUID> {

//    Optional<AuthorHistory> findOne(UUID id);
//
//    Optional<List<AuthorHistory>> findAll();

    Optional<AuthorHistory> save(AuthorHistory author);
}