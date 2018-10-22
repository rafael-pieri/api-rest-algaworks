package com.algaworks.socialbooks.dto.author;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class AuthorPostObjectDTO {

    @NotEmpty(message = "The field name is required.")
    private String name;

    @NotNull(message = "The field nationality is required.")
    private String nationality;

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

    public static class AuthorPostObjectBuilderDTO {

        private AuthorPostObjectDTO authorPostObjectDTO = new AuthorPostObjectDTO();

        public AuthorPostObjectBuilderDTO withName(final String name) {
            this.authorPostObjectDTO.name = name;
            return this;
        }

        public AuthorPostObjectBuilderDTO withNationality(final String nationality) {
            this.authorPostObjectDTO.nationality = nationality;
            return this;
        }

        public AuthorPostObjectDTO build() {
            return this.authorPostObjectDTO;
        }
    }
}