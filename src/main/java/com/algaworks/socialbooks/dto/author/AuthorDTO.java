package com.algaworks.socialbooks.dto.author;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AuthorDTO {

    private UUID id;
    private String name;
    private String nationality;
}