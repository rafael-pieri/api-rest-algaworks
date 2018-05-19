package com.algaworks.socialbooks.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class AuthorPostObjectDTO {

    @NotEmpty(message = "The field name is required.")
    private String name;

    @ApiModelProperty(required = true, example = "2018-01-01")
    @NotNull(message = "The field birth is required.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @NotNull(message = "The field nationality is required.")
    private String nationality;

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

    public static class AuthorPostObjectBuilderDTO {

        private AuthorPostObjectDTO authorPostObjectDTO = new AuthorPostObjectDTO();

        public AuthorPostObjectBuilderDTO withName(String name) {
            this.authorPostObjectDTO.name = name;
            return this;
        }

        public AuthorPostObjectBuilderDTO withBirth(Date birth) {
            this.authorPostObjectDTO.birth = birth;
            return this;
        }

        public AuthorPostObjectBuilderDTO withNationality(String nationality) {
            this.authorPostObjectDTO.nationality = nationality;
            return this;
        }

        public AuthorPostObjectDTO build() {
            return this.authorPostObjectDTO;
        }
    }
}