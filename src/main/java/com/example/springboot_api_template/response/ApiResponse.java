package com.example.springboot_api_template.response;

import java.time.LocalDateTime;

public class ApiResponse<T> {

	public static final String SUCCESS = "success";
	public static final String NOT_FOUND = "not found";
	public static final String ERROR = "error";
	public static final String UNAUTHORIZED = "unauthorized";

	private String status;
	private String message;
	private LocalDateTime timestamp;
	private T data;

	public ApiResponse() {
		super();
	}

	public ApiResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}

	public ApiResponse(String status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
