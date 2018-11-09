package com.algaworks.socialbooks.model.book;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.socialbooks.model.author.Author;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.hibernate.annotations.Type;

@Entity
public class BookHistory {

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
    private ZonedDateTime createdAt;

    @Type(type = "org.hibernate.type.ZonedDateTimeType")
    private ZonedDateTime deletedAt;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(final Date publication) {
        this.publication = publication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(final ZonedDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public static class BookHistoryBuilder {

        private final BookHistory bookHistory = new BookHistory();

        public BookHistoryBuilder withId(final UUID id) {
            this.bookHistory.id = id;
            return this;
        }

        public BookHistoryBuilder withName(final String name) {
            this.bookHistory.name = name;
            return this;
        }

        public BookHistoryBuilder withPublication(final Date publication) {
            this.bookHistory.publication = publication;
            return this;
        }

        public BookHistoryBuilder withPublisher(final String publisher) {
            this.bookHistory.publisher = publisher;
            return this;
        }

        public BookHistoryBuilder withSummary(final String summary) {
            this.bookHistory.summary = summary;
            return this;
        }

        public BookHistoryBuilder withComments(final List<Comment> comments) {
            this.bookHistory.comments = comments;
            return this;
        }

        public BookHistoryBuilder withAuthor(final Author author) {
            this.bookHistory.author = author;
            return this;
        }

        public BookHistoryBuilder withCreatedAt(final ZonedDateTime createdAt) {
            this.bookHistory.createdAt = createdAt;
            return this;
        }

        public BookHistoryBuilder withDeletedAt(final ZonedDateTime deletedAt) {
            this.bookHistory.deletedAt = deletedAt;
            return this;
        }

        public BookHistory build() {
            return this.bookHistory;
        }
    }
}