package com.socialbooks.repository;

import com.socialbooks.model.book.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}