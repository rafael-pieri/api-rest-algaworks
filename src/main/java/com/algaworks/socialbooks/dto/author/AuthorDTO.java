package com.algaworks.socialbooks.dto.author;

import java.util.UUID;

public class AuthorDTO {

    private UUID id;
    private String name;
    private String nationality;

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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public static class AuthorBuilderDTO {

        private AuthorDTO authorDTO = new AuthorDTO();

        public AuthorBuilderDTO withId(final UUID id) {
            this.authorDTO.id = id;
            return this;
        }

        public AuthorBuilderDTO withName(final String name) {
            this.authorDTO.name = name;
            return this;
        }

        public AuthorBuilderDTO withNationality(final String nationality) {
            this.authorDTO.nationality = nationality;
            return this;
        }

        public AuthorDTO build() {
            return this.authorDTO;
        }
    }
}