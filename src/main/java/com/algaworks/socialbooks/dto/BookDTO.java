package com.algaworks.socialbooks.dto;

import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.model.book.Comment;
import java.util.Date;
import java.util.List;

public class BookDTO {

    private Long id;

    private String name;

    private Date publication;

    private String publisher;

    private String summary;

    private List<Comment> comments;

    private Author author;

    public BookDTO() {
    }

    public BookDTO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}