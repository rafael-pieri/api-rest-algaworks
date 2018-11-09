package com.algaworks.socialbooks.dto.book;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookPutObjectDTO {

    private String name;
    private Date publication;
    private String publisher;
    private String summary;
    private UUID authorId;

    @JsonCreator
    public BookPutObjectDTO(@JsonProperty("name") final String name,
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