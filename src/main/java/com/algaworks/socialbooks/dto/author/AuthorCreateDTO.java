package com.algaworks.socialbooks.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorCreateDTO {

    @NotNull
    private UUID id;

    @NotNull
    private ZonedDateTime lastModified;
}