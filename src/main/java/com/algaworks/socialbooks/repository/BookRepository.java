package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

}