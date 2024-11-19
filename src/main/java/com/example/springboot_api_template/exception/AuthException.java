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
	public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex) {
		return responseException("Token is expired");
	}

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<Object> handleSignatureException(SignatureException ex) {
		return responseException("Token isn't valid");
	}

	private ResponseEntity<Object> responseException(Object message) {
		Map<Object, Object> errorResponse = new HashMap<>();
		errorResponse.put("status", ApiResponse.UNAUTHORIZED);
		errorResponse.put("message", message);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
}
