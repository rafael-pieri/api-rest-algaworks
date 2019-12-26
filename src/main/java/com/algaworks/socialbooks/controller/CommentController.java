package com.algaworks.socialbooks.controller;

import com.algaworks.socialbooks.model.book.Comment;
import com.algaworks.socialbooks.services.CommentService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/books")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/{id}/comments")
    public ResponseEntity<Void> addComments(@PathVariable("id") final UUID bookId,
                                            @RequestBody final Comment comment) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        comment.setUser(auth.getName());
        comment.setUser("Rafael");
        this.commentService.saveComment(bookId, comment);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> listComments(@PathVariable("id") final UUID bookId) {
        return this.commentService.listComment(bookId);
    }
}