package com.algaworks.socialbooks.model.author;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.algaworks.socialbooks.model.book.Book;
import org.hibernate.annotations.Type;

@Entity
public class Author {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(columnDefinition = "VARCHAR(36)", length = 36)
    private UUID id;

    @NotEmpty
    private String name;

    @NotNull
    private String nationality;

    @Type(type = "org.hibernate.type.ZonedDateTimeType")
    private ZonedDateTime modifiedAt;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(final String nationality) {
        this.nationality = nationality;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(final ZonedDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public static class AuthorBuilder {

        private final  Author author = new Author();

        public AuthorBuilder withId(final UUID id) {
            this.author.id = id;
            return this;
        }

        public AuthorBuilder withName(final String name) {
            this.author.name = name;
            return this;
        }

        public AuthorBuilder withNationality(final String nationality) {
            this.author.nationality = nationality;
            return this;
        }

        public AuthorBuilder withModifiedAt(final ZonedDateTime modifiedAt) {
            this.author.modifiedAt = modifiedAt;
            return this;
        }

        public Author build() {
            return this.author;
        }
    }
}