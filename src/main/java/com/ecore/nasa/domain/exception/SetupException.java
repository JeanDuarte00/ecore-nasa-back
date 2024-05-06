package com.ecore.nasa.domain.exception;

import org.springframework.http.HttpStatus;

public class SetupException extends ApplicationException{
	public SetupException (String message) {
		super(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
