package com.algaworks.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.socialbooks.domain.Author;

public interface AuthorsRepository extends JpaRepository<Author, Long>{

}
