package com.example.springboot_api_template.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.springboot_api_template.response.ApiResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class AuthException {
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Map<String, String>> handleExpiredJwtException(ExpiredJwtException ex) {
		return buildErrorResponse("Token is expired", HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<Map<String, String>> handleSignatureException(SignatureException ex) {
		return buildErrorResponse("Token isn't valid", HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
		return buildErrorResponse("Token validation error", HttpStatus.UNAUTHORIZED);
	}

	private ResponseEntity<Map<String, String>> buildErrorResponse(String message, HttpStatus status) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("status", ApiResponse.UNAUTHORIZED);
		errorResponse.put("message", message);
		return new ResponseEntity<>(errorResponse, status);
	}
}
