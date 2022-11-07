package com.novare.tredara.exceptions;

import org.springframework.http.HttpStatus;

public class TredaraException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;

	public TredaraException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public TredaraException(String message, HttpStatus status, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
}