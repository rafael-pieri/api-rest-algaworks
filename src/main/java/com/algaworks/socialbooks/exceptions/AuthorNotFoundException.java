package com.algaworks.socialbooks.exceptions;

public class AuthorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorNotFoundException() {}

	public AuthorNotFoundException(String message) {
		super(message);
	}

	public AuthorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}