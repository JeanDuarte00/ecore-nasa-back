package com.ecore.nasa.infrastructure.config;

import com.ecore.nasa.domain.exception.ApplicationException;
import com.ecore.nasa.domain.exception.InvalidPayloadException;
import com.ecore.nasa.domain.exception.SetupException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = {SetupException.class, InvalidPayloadException.class})
	protected ResponseEntity<Object> handleConflict(ApplicationException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ex.getStatusCode(), request);
	}
}
