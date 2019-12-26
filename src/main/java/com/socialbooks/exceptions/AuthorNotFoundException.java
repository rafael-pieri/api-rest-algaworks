package com.socialbooks.exceptions;

public class AuthorNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthorNotFoundException(final String message) {
        super(message);
    }
}