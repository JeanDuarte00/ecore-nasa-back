package com.ecore.nasa.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidPayloadException extends ApplicationException {
	public InvalidPayloadException (String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
