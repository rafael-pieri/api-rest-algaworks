package com.socialbooks.services;

import com.socialbooks.dto.comment.CommentCreateDTO;
import com.socialbooks.dto.comment.CommentDTO;
import com.socialbooks.dto.comment.CommentPostDTO;
import com.socialbooks.model.book.Comment;
import com.socialbooks.repository.CommentRepository;
import com.socialbooks.services.book.BookService;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BookService bookService;

    public CommentService(final CommentRepository commentRepository,
                          final BookService bookService) {
        this.bookService = bookService;
        this.commentRepository = commentRepository;
    }

    public CommentCreateDTO save(final UUID bookId, final CommentPostDTO commentPostDTO) {
        final Comment comment = this.commentRepository.save(
                Comment.builder()
                        .user(commentPostDTO.getUser())
                        .book(this.bookService.getBook(bookId))
                        .text(commentPostDTO.getText())
                        .createdAt(ZonedDateTime.now(ZoneOffset.UTC))
                        .build()
        );

        return new CommentCreateDTO(comment.getId(), comment.getCreatedAt());
    }

    public List<CommentDTO> list(final UUID bookId) {
        return this.bookService.getBook(bookId).getComments().stream()
                .map(comment -> CommentDTO.builder()
                        .id(comment.getId())
                        .user(comment.getUser())
                        .text(comment.getText())
                        .createdAt(comment.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}