package com.algaworks.socialbooks.controller;

import com.algaworks.socialbooks.model.book.Comment;
import com.algaworks.socialbooks.services.CommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.POST)
    public ResponseEntity<Void> addComments(@PathVariable("id") Long bookId, @RequestBody Comment comment) {
//        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        comment.setUser(auth.getName());
        comment.setUser("Rafael");
        commentService.saveComment(bookId, comment);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> listComments(@PathVariable("id") Long bookId) {
        return commentService.listComment(bookId);
    }
}
