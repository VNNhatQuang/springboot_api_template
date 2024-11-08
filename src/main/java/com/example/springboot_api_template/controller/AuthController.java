package com.example.springboot_api_template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_api_template.model.User;
import com.example.springboot_api_template.request.LoginRequest;
import com.example.springboot_api_template.response.ApiResponse;
import com.example.springboot_api_template.service.AuthService;
import com.example.springboot_api_template.util.JwtUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
		try {
			User user = authService.checkLogin(loginRequest);
			if (user != null) { // Đăng nhập thành công
				String token = jwtUtil.generateToken(user.getUserName());

				ApiResponse<Object> successResponse = new ApiResponse<Object>(ApiResponse.SUCCESS, "Login", token);
				return ResponseEntity.ok(successResponse);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			System.out.println(e);
			ApiResponse<Object> errorResponse = new ApiResponse<Object>(ApiResponse.ERROR, "Internal server error");
			return ResponseEntity.internalServerError().body(errorResponse);
		}
	}

}
