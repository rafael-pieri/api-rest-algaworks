package com.algaworks.socialbooks.model.author;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Temporal(TemporalType.DATE)
    private Date birth;

    @NotNull
    private String nationality;

    @Type(type = "org.hibernate.type.ZonedDateTimeType")
    private ZonedDateTime createdAt = ZonedDateTime.now(ZoneOffset.UTC);

    @Type(type = "org.hibernate.type.ZonedDateTimeType")
    private ZonedDateTime deletedAt;

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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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

        private AuthorHistory authorHistory = new AuthorHistory();

        public AuthorHistoryBuilder withAuthorId(UUID authorId) {
            this.authorHistory.authorId = authorId;
            return this;
        }

        public AuthorHistoryBuilder withName(String name) {
            this.authorHistory.name = name;
            return this;
        }

        public AuthorHistoryBuilder withBirth(Date birth) {
            this.authorHistory.birth = birth;
            return this;
        }

        public AuthorHistoryBuilder withNationality(String nationality) {
            this.authorHistory.nationality = nationality;
            return this;
        }

        public AuthorHistoryBuilder withCreatedAt(ZonedDateTime createdAt) {
            this.authorHistory.createdAt = createdAt;
            return this;
        }

        public AuthorHistoryBuilder withDeletedAt(ZonedDateTime deletedAt) {
            this.authorHistory.deletedAt = deletedAt;
            return this;
        }

        public AuthorHistory build() {
            return this.authorHistory;
        }
    }
}