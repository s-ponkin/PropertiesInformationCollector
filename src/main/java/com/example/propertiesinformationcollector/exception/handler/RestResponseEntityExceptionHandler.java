package com.example.propertiesinformationcollector.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PropertiesNotFoundException.class)
	protected ErrorBody handleProductNotFoundException(PropertiesNotFoundException exception) {
		return new ErrorBody(exception.getMessage());
	}
}
