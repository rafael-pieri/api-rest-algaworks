package com.algaworks.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.socialbooks.domain.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Long>{

}
