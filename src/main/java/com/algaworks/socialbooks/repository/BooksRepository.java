package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.model.book.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface BooksRepository extends Repository<Book, Long> {

    Optional<Book> findOne(Long id);

    Optional<List<Book>> findAll();

    Optional<Book> save(Book book);

    void delete(Long id);
}