package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.dto.CommentDTO;
import com.algaworks.socialbooks.exceptions.BookNotFoundException;
import com.algaworks.socialbooks.model.book.Book;
import com.algaworks.socialbooks.model.book.Comment;
import com.algaworks.socialbooks.repository.BookRepository;
import com.algaworks.socialbooks.repository.CommentRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    public CommentDTO saveComment(Long bookId, Comment comment) {
        Optional<Book> book = bookRepository.findOne(bookId);

        if(!book.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        comment.setBook(book.get());
        comment.setDate(new Date());

        Optional<Comment> save = commentRepository.save(comment);
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(save, commentDTO);
        return commentDTO;
    }

    public List<Comment> listComment(Long bookId) {
        Optional<Book> book = bookRepository.findOne(bookId);

        if(!book.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        return book.get().getComments();
    }
}