package com.algaworks.socialbooks.model.author;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class AuthorHistory {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(columnDefinition = "VARCHAR(36)", length = 36)
    private UUID id;

    @Type(type = "uuid-char")
    @Column(columnDefinition = "VARCHAR(36)", length = 36)
    private UUID authorId;

    @NotEmpty
    private String name;

    @NotNull
    private String nationality;

    @Type(type = "org.hibernate.type.ZonedDateTimeType")
    private ZonedDateTime createdAt;

    @Type(type = "org.hibernate.type.ZonedDateTimeType")
    private ZonedDateTime deletedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(ZonedDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public static class AuthorHistoryBuilder {

        private final AuthorHistory authorHistory = new AuthorHistory();

        public AuthorHistoryBuilder withId(final UUID id) {
            this.authorHistory.id = id;
            return this;
        }

        public AuthorHistoryBuilder withAuthorId(final UUID authorId) {
            this.authorHistory.authorId = authorId;
            return this;
        }

        public AuthorHistoryBuilder withName(final String name) {
            this.authorHistory.name = name;
            return this;
        }

        public AuthorHistoryBuilder withNationality(final String nationality) {
            this.authorHistory.nationality = nationality;
            return this;
        }

        public AuthorHistoryBuilder withCreatedAt(final ZonedDateTime createdAt) {
            this.authorHistory.createdAt = createdAt;
            return this;
        }

        public AuthorHistoryBuilder withDeletedAt(final ZonedDateTime deletedAt) {
            this.authorHistory.deletedAt = deletedAt;
            return this;
        }

        public AuthorHistory build() {
            return this.authorHistory;
        }
    }
}