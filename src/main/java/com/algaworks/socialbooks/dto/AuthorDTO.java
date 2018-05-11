package com.algaworks.socialbooks.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.UUID;

public class AuthorDTO {

    private UUID id;

    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date birth;

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

    public static class AuthorBuilderDTO {

        private AuthorDTO authorDTO = new AuthorDTO();

        public AuthorBuilderDTO withId(UUID id) {
            this.authorDTO.id = id;
            return this;
        }

        public AuthorBuilderDTO withName(String name) {
            this.authorDTO.name = name;
            return this;
        }

        public AuthorBuilderDTO withBirth(Date birth) {
            this.authorDTO.birth = birth;
            return this;
        }

        public AuthorBuilderDTO withNationality(String nationality) {
            this.authorDTO.nationality = nationality;
            return this;
        }

        public AuthorDTO build() {
            return this.authorDTO;
        }
    }
}