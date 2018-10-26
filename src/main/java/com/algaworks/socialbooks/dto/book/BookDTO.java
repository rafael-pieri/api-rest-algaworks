package com.algaworks.socialbooks.dto.book;

import com.algaworks.socialbooks.dto.author.AuthorDTO;
import com.algaworks.socialbooks.model.book.Comment;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@JsonPropertyOrder({"id", "name", "publication", "publisher", "summary", "author", "comments"})
public class BookDTO {

    private UUID id;
    private String name;
    private Date publication;
    private String publisher;
    private String summary;
    private AuthorDTO author;
    private List<Comment> comments;
}