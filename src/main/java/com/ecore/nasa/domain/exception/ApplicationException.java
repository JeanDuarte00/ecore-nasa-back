package com.ecore.nasa.domain.exception;

import org.springframework.http.HttpStatusCode;

public class ApplicationException extends RuntimeException {
	private final HttpStatusCode statusCode;
	public ApplicationException (String message, HttpStatusCode code) {
		super(message);
		this.statusCode = code;
	}
	public HttpStatusCode getStatusCode () {
		return statusCode;
	}
}