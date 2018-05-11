package com.algaworks.socialbooks.exceptions;

public class AuthorAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorAlreadyExistsException(String message) {
		super(message);
	}

	public AuthorAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}