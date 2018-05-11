package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.model.book.Comment;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface CommentsRepository extends Repository<Comment, Long> {

    Optional<Comment> save(Comment comment);
}