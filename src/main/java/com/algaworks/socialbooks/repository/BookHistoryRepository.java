package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.model.book.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {

}