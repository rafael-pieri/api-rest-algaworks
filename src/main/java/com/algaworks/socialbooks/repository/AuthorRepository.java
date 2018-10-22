package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.model.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}