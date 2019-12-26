package com.socialbooks.dto.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class BookPostObjectDTO {

    private String name;
    private Date publication;
    private String publisher;
    private String summary;
    private UUID authorId;

    @JsonCreator
    public BookPostObjectDTO(@JsonProperty("name") final String name,
                             @JsonProperty("publication") final Date publication,
                             @JsonProperty("publisher") final String publisher,
                             @JsonProperty("summary") final String summary,
                             @JsonProperty("authorId") final UUID authorId) {
        this.name = name;
        this.publication = publication;
        this.publisher = publisher;
        this.summary = summary;
        this.authorId = authorId;
    }
}