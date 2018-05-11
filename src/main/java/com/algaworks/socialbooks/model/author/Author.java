package com.algaworks.socialbooks.model.author;

import com.algaworks.socialbooks.model.book.Book;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

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
    @Temporal(TemporalType.DATE)
    private Date birth;

    @NotNull
    private String nationality;

    @Type(type = "org.hibernate.type.ZonedDateTimeType")
    private ZonedDateTime modifiedAt = ZonedDateTime.now(ZoneOffset.UTC);

    @OneToMany(mappedBy = "author")
    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(ZonedDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public static class AuthorBuilder {

        private Author author = new Author();

        public AuthorBuilder withName(String name) {
            this.author.name = name;
            return this;
        }

        public AuthorBuilder withBirth(Date birth) {
            this.author.birth = birth;
            return this;
        }

        public AuthorBuilder withNationality(String nationality) {
            this.author.nationality = nationality;
            return this;
        }

        public Author build() {
            return this.author;
        }
    }
}