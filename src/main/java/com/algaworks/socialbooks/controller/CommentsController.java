package com.algaworks.socialbooks.controller;

import com.algaworks.socialbooks.model.book.Comment;
import com.algaworks.socialbooks.services.CommentsService;
import com.wordnik.swagger.annotations.Api;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(value = "comments")
@RestController
@RequestMapping("/api/books")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.POST)
    public ResponseEntity<Void> addComments(@PathVariable("id") Long bookId, @RequestBody Comment comment) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        comment.setUser(auth.getName());

        commentsService.saveComment(bookId, comment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> listComments(@PathVariable("id") Long bookId) {

        return commentsService.listComment(bookId);
    }
}
