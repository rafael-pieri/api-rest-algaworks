package com.algaworks.socialbooks.model.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.hibernate.annotations.Type;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}