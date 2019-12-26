package com.socialbooks.dto.author;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorPutObjectDTO {

    private String name;
    private String nationality;

    @JsonCreator
    public AuthorPutObjectDTO(@JsonProperty("name") final String name,
                              @JsonProperty("nationality") final String nationality) {
        this.name = name;
        this.nationality = nationality;
    }
}