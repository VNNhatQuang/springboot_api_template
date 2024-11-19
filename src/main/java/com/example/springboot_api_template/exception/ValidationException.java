package com.example.springboot_api_template.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.springboot_api_template.response.ApiResponse;

@ControllerAdvice
public class ValidationException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return responseException(errors);
	}

	private ResponseEntity<Object> responseException(Object message) {
		Map<Object, Object> errorResponse = new HashMap<>();
		errorResponse.put("status", ApiResponse.BAD_REQUEST);
		errorResponse.put("message", message);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
