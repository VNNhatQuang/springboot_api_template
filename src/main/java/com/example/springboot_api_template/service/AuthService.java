package com.example.springboot_api_template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot_api_template.model.User;
import com.example.springboot_api_template.request.LoginRequest;

@Service
public class AuthService {

	@Autowired
	private UserService userService;

	/**
	 * Hàm kiểm tra đăng nhập
	 * 
	 * @param loginRequest
	 * @return
	 */
	public User checkLogin(LoginRequest loginRequest) {
		User user = userService.getUserByUserName(loginRequest.getUserName());
		if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
			return user;
		}
		return null;
	}

}
