package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.dto.CommentDTO;
import com.algaworks.socialbooks.exceptions.BookNotFoundException;
import com.algaworks.socialbooks.model.book.Book;
import com.algaworks.socialbooks.model.book.Comment;
import com.algaworks.socialbooks.repository.BooksRepository;
import com.algaworks.socialbooks.repository.CommentsRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    public CommentDTO saveComment(Long bookId, Comment comment) {
        Optional<Book> book = booksRepository.findOne(bookId);

        if(!book.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        comment.setBook(book.get());
        comment.setDate(new Date());

        Optional<Comment> save = commentsRepository.save(comment);
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(save, commentDTO);
        return commentDTO;
    }

    public List<Comment> listComment(Long bookId) {
        Optional<Book> book = booksRepository.findOne(bookId);

        if(!book.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        return book.get().getComments();
    }
}