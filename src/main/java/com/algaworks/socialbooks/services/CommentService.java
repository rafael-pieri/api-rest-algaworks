package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.exceptions.BookNotFoundException;
import com.algaworks.socialbooks.model.book.Book;
import com.algaworks.socialbooks.model.book.Comment;
import com.algaworks.socialbooks.repository.BookRepository;
import com.algaworks.socialbooks.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(final BookRepository bookRepository,
                          final CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    public void saveComment(Long bookId, Comment comment) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (!book.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        comment.setBook(book.get());
        comment.setDate(new Date());

        commentRepository.save(comment);
    }

    public List<Comment> listComment(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (!book.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        return book.get().getComments();
    }
}