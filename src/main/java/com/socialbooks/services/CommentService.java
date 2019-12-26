package com.socialbooks.services;

import com.socialbooks.exceptions.BookNotFoundException;
import com.socialbooks.model.book.Book;
import com.socialbooks.model.book.Comment;
import com.socialbooks.repository.BookRepository;
import com.socialbooks.repository.CommentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public CommentService(final BookRepository bookRepository,
                          final CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    public void saveComment(final UUID bookId, final Comment comment) {
        final Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (!bookOptional.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        comment.setBook(bookOptional.get());
        comment.setDate(new Date());

        commentRepository.save(comment);
    }

    public List<Comment> listComment(final UUID bookId) {
        final Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (!bookOptional.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        return bookOptional.get().getComments();
    }
}