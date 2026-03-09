package com.productinfo.exception;

import java.time.LocalDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.productinfo.model.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected  ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//		LocalDateTime timestamp = LocalDateTime.now();
//		String exception = ex.getMessage();
//		int statuscode = status.value();
//		String errorMessage = HttpStatus.valueOf(ex.getStatusCode().value()).name(); 
		String errorMessage = HttpStatus.METHOD_NOT_ALLOWED.name();
		ApiErrors apiErrors = ApiErrors.builder().build();
		apiErrors.setErrorMessage(errorMessage);
		apiErrors.setException(ex.getMessage());
		apiErrors.setTimestamp(LocalDateTime.now());
		apiErrors.setStatus(status.value());
		return ResponseEntity.status(status).body(apiErrors);
	}

	@Override
	protected  ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String errorMessage = HttpStatus.UNSUPPORTED_MEDIA_TYPE.name();

		ApiErrors apiErrors = ApiErrors.builder().build();
		apiErrors.setErrorMessage(errorMessage);
		apiErrors.setException(ex.getMessage());
		apiErrors.setTimestamp(LocalDateTime.now());
		apiErrors.setStatus(status.value());
		return ResponseEntity.status(status).body(apiErrors);
	}

	@Override
	protected  ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String errorMessage = HttpStatus.valueOf(ex.getStatusCode().value()).name();

		ApiErrors apiErrors = ApiErrors.builder().build();
		apiErrors.setErrorMessage(errorMessage);
		apiErrors.setException(ex.getMessage());
		apiErrors.setTimestamp(LocalDateTime.now());
		apiErrors.setStatus(status.value());
		return ResponseEntity.status(status).body(apiErrors);
	}

	@Override
	protected  ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status,
			WebRequest request) {
		String errorMessage = HttpStatus.BAD_REQUEST.name();

		ApiErrors apiErrors = ApiErrors.builder().build();
		apiErrors.setErrorMessage(errorMessage);
		apiErrors.setException(ex.getMessage());
		apiErrors.setTimestamp(LocalDateTime.now());
		apiErrors.setStatus(status.value());
		return ResponseEntity.status(status).body(apiErrors);

	}

	@Override
	protected  ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		String errorMessage = ex.getErrorCode();

		ApiErrors apiErrors = ApiErrors.builder().build();
		apiErrors.setErrorMessage(errorMessage);
		apiErrors.setException(ex.getMessage());
		apiErrors.setTimestamp(LocalDateTime.now());
		apiErrors.setStatus(status.value());
		return ResponseEntity.status(status).body(apiErrors);
	}

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e) {
		String errorMessage = "Exception occured";
        int statuscode = HttpStatus.INTERNAL_SERVER_ERROR.value();
		
		ApiErrors apiErrors = ApiErrors.builder().build();
		apiErrors.setErrorMessage(errorMessage);
		apiErrors.setException(e.getMessage());
		apiErrors.setTimestamp(LocalDateTime.now());
		apiErrors.setStatus(statuscode);
		return ResponseEntity.status(statuscode).body(apiErrors);
	}

}






