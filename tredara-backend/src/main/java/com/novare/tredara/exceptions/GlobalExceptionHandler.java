package com.novare.tredara.exceptions;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(TredaraException.class)
	public ResponseEntity<?> tredaraAPIException(TredaraException ex, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getStatus().toString(), ex.getMessage(),
				request.getDescription(false));
		LOGGER.error("An exception occured, which will cause a {} response", ex.getStatus(), ex);
		return new ResponseEntity<>(errorDetails, ex.getStatus());
	}

	@ExceptionHandler({ AccessDeniedException.class, BadCredentialsException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		ErrorResponse errorDetails = getErrorResponse(ex, request, HttpStatus.UNAUTHORIZED);
		LOGGER.error("An exception occured, which will cause a {} response", HttpStatus.UNAUTHORIZED, ex);
		return new ResponseEntity<Object>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorResponse errorDetails = getErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
		LOGGER.error("An exception occured, which will cause a {} response", HttpStatus.INTERNAL_SERVER_ERROR, ex);
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
		LOGGER.error("An exception occured, which will cause a {} response", HttpStatus.BAD_REQUEST, ex);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}