package com.algaworks.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.socialbooks.domain.Book;

public interface LivrosRepository extends JpaRepository<Book, Long>{

}
