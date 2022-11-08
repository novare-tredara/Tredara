package com.novare.tredara.exceptions;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TredaraException.class)
	public ResponseEntity<?> natflixAPIException(TredaraException ex, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getStatus().toString(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, ex.getStatus());
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		ErrorResponse errorDetails = getErrorResponse(ex, request, HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<Object>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
 		ErrorResponse errorDetails = getErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorResponse getErrorResponse(Exception ex, WebRequest request, HttpStatus status) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), status.toString(), ex.getMessage(),
				request.getDescription(false));
		return errorDetails;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}