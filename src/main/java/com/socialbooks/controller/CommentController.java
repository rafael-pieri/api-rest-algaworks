package com.socialbooks.controller;

import com.socialbooks.dto.comment.CommentCreateDTO;
import com.socialbooks.dto.comment.CommentDTO;
import com.socialbooks.dto.comment.CommentPostDTO;
import com.socialbooks.services.CommentService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/{id}/comments")
    public CommentCreateDTO addComments(@PathVariable("id") final UUID bookId,
                                        @RequestBody final CommentPostDTO commentPostDTO) {
        return this.commentService.save(bookId, commentPostDTO);
    }

    @GetMapping(value = "/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDTO> listComments(@PathVariable("id") final UUID bookId) {
        return this.commentService.list(bookId);
    }
}