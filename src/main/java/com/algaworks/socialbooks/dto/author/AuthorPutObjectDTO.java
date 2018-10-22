package com.algaworks.socialbooks.dto.author;

public class AuthorPutObjectDTO {

    private String name;
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

        private AuthorPutObjectDTO authorPostObjectDTO = new AuthorPutObjectDTO();

        public AuthorPostObjectBuilderDTO withName(final String name) {
            this.authorPostObjectDTO.name = name;
            return this;
        }

        public AuthorPostObjectBuilderDTO withNationality(final String nationality) {
            this.authorPostObjectDTO.nationality = nationality;
            return this;
        }

        public AuthorPutObjectDTO build() {
            return this.authorPostObjectDTO;
        }
    }
}