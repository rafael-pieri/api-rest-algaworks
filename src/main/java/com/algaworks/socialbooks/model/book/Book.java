package com.algaworks.socialbooks.model.book;

import com.algaworks.socialbooks.model.author.Author;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Book {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(columnDefinition = "VARCHAR(36)", length = 36)
    private UUID id;

    @NotEmpty(message = "The field name is required.")
    private String name;

    @NotNull(message = "The field publication is required.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date publication;

    @NotNull(message = "The field publisher is required.")
    private String publisher;

    @NotNull(message = "The field summary is required.")
    @Size(max = 1500, message = "The field summary can not contain more than 1500 characters.")
    private String summary;

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(mappedBy = "book")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    @JsonInclude(Include.NON_NULL)
    private Author author;

    @Type(type = "org.hibernate.type.ZonedDateTimeType")
    private ZonedDateTime modifiedAt;

    public Book() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(ZonedDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public static class BookBuilder {

        private Book book = new Book();

        public BookBuilder withId(final UUID id) {
            this.book.id = id;
            return this;
        }

        public BookBuilder withName(final String name) {
            this.book.name = name;
            return this;
        }

        public BookBuilder withPublication(final Date publication) {
            this.book.publication = publication;
            return this;
        }

        public BookBuilder withPublisher(final String publisher) {
            this.book.publisher = publisher;
            return this;
        }

        public BookBuilder withSummary(final String summary) {
            this.book.summary = summary;
            return this;
        }

        public BookBuilder withAuthor(final Author author) {
            this.book.author = author;
            return this;
        }

        public BookBuilder withModifiedAt(final ZonedDateTime modifiedAt) {
            this.book.modifiedAt = modifiedAt;
            return this;
        }

        public Book build() {
            return this.book;
        }
    }
}