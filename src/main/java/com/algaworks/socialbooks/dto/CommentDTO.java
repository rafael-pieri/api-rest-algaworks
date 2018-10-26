package com.algaworks.socialbooks.dto;

import com.algaworks.socialbooks.model.book.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDTO {

    private Long id;
    private String text;
    private String user;
    private Date date;
    private Book book;
}