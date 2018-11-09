package com.algaworks.socialbooks.exceptions;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotFoundException(final String message) {
		super(message);
	}
}