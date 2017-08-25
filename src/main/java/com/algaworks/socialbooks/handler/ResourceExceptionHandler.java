package com.algaworks.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.socialbooks.domain.ErrorDetails;
import com.algaworks.socialbooks.services.exceptions.AuthorAlreadyExistsException;
import com.algaworks.socialbooks.services.exceptions.AuthorNotFoundException;
import com.algaworks.socialbooks.services.exceptions.BookNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleBookNotFoundException(BookNotFoundException bookNotFoundException,
			HttpServletRequest request) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404L);
		errorDetails.setTitle("The book could not be found.");
		errorDetails.setDeveloperMessage("http://errors.socialbooks.com/404");
		errorDetails.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}

	@ExceptionHandler(AuthorAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> handleAuthorAlreadyExistsException(
			AuthorAlreadyExistsException authorAlreadyExistsException, HttpServletRequest request) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(409L);
		errorDetails.setTitle("The author already exists.");
		errorDetails.setDeveloperMessage("http://errors.socialbooks.com/409");
		errorDetails.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
	}

	@ExceptionHandler(AuthorNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleAuthorNotFoundException(AuthorNotFoundException authorNotFoundException,
			HttpServletRequest request) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404L);
		errorDetails.setTitle("The author could not be found.");
		errorDetails.setDeveloperMessage("http://errors.socialbooks.com/404");
		errorDetails.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(
			DataIntegrityViolationException dataIntegrityViolationException, HttpServletRequest request) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400L);
		errorDetails.setTitle("Invalid request.");
		errorDetails.setDeveloperMessage("http://errors.socialbooks.com/400");
		errorDetails.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
}
