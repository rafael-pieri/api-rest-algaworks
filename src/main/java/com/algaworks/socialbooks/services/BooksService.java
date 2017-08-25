package com.algaworks.socialbooks.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Comment;
import com.algaworks.socialbooks.domain.Book;
import com.algaworks.socialbooks.repository.CommentsRepository;
import com.algaworks.socialbooks.repository.BooksRepository;
import com.algaworks.socialbooks.services.exceptions.BookNotFoundException;

@Service
public class BooksService {

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private CommentsRepository commentsRepository;

	public List<Book> list() {
		return booksRepository.findAll();
	}

	public Book findById(Long id) {
		Book book = booksRepository.findOne(id);

		if (book == null) {
			throw new BookNotFoundException("The book could not be found.");
		}

		return book;
	}

	public Book save(Book book) {
		book.setId(null);
		return booksRepository.save(book);
	}

	public void delete(Long id) {
		try {
			booksRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BookNotFoundException("The book could not be found.");
		}
	}

	public void update(Book book) {
		verifyExistence(book);
		booksRepository.save(book);
	}

	private void verifyExistence(Book book) {
		findById(book.getId());
	}

	public Comment saveComment(Long bookId, Comment comment) {
		Book book = findById(bookId);

		comment.setBook(book);
		comment.setDate(new Date());

		return commentsRepository.save(comment);
	}

	public List<Comment> listComment(Long bookId) {
		Book book = findById(bookId);
		return book.getComments();
	}

}
