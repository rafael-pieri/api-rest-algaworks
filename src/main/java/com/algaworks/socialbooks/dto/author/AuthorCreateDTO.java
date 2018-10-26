package com.algaworks.socialbooks.dto.author;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthorCreateDTO {

    private final @NonNull UUID id;
    private final @NonNull ZonedDateTime lastModified;
}