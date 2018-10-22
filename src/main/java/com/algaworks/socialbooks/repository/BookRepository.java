package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}