package com.example.springboot_api_template.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateUserRequest {

	@NotBlank(message = "userName is require")
	private String userName;
	@NotBlank(message = "email is require")
	@Email(message = "Invalid email format")
	private String email;
	@NotBlank(message = "phoneNumber is require")
	private String phoneNumber;
	@NotBlank(message = "password is require")
	private String password;

	public UpdateUserRequest(String userName, String email, String phoneNumber, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
