package com.example.springboot_api_template.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank(message = "userName is require")
	private String userName;
	@NotBlank(message = "password is require")
	private String password;

	public LoginRequest() {
		super();
	}

	public LoginRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
