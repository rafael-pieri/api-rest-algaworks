package com.algaworks.socialbooks.handler;

import com.algaworks.socialbooks.dto.ErrorDetails;
import com.algaworks.socialbooks.exceptions.AuthorAlreadyExistsException;
import com.algaworks.socialbooks.exceptions.AuthorNotFoundException;
import com.algaworks.socialbooks.exceptions.BookNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDetails handleBookNotFoundException(BookNotFoundException exception) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(404L);
        errorDetails.setTitle("The book could not be found.");
        errorDetails.setDeveloperMessage("http://errors.socialbooks.com/404");
        errorDetails.setTimestamp(System.currentTimeMillis());

        return errorDetails;
    }

    @ExceptionHandler(AuthorAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorDetails handleAuthorAlreadyExistsException(AuthorAlreadyExistsException exception) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(409L);
        errorDetails.setTitle("The author already exists.");
        errorDetails.setDeveloperMessage("http://errors.socialbooks.com/409");
        errorDetails.setTimestamp(System.currentTimeMillis());

        return errorDetails;
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDetails handleAuthorNotFoundException(AuthorNotFoundException exception) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(404L);
        errorDetails.setTitle("The author could not be found.");
        errorDetails.setDeveloperMessage("http://errors.socialbooks.com/404");
        errorDetails.setTimestamp(System.currentTimeMillis());

        return errorDetails;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDetails handleDataIntegrityViolationException(DataIntegrityViolationException exception) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(400L);
        errorDetails.setTitle("Invalid request.");
        errorDetails.setDeveloperMessage("http://errors.socialbooks.com/400");
        errorDetails.setTimestamp(System.currentTimeMillis());

        return errorDetails;
    }
}