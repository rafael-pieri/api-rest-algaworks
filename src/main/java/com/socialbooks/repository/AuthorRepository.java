package com.socialbooks.repository;

import com.socialbooks.model.author.Author;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}