package com.example.springboot_api_template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_api_template.model.User;
import com.example.springboot_api_template.request.StoreUserRequest;
import com.example.springboot_api_template.request.UpdateUserRequest;
import com.example.springboot_api_template.response.ApiResponse;
import com.example.springboot_api_template.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public ResponseEntity<Object> getAllUsers() {
		try {
			List<User> users = userService.getAllUsers();
			ApiResponse<List<User>> successResponse = new ApiResponse<>(ApiResponse.SUCCESS, "Fetched all users successfully", users);
			return ResponseEntity.ok(successResponse);
		} catch (Exception e) {
			System.out.println(e);
			ApiResponse<Object> errorResponse = new ApiResponse<>(ApiResponse.ERROR, "Internal server error");
			return ResponseEntity.internalServerError().body(errorResponse);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Long id) {
		try {
			User user = userService.getUserById(id);

			if (user != null) {
				ApiResponse<User> successResponse = new ApiResponse<>(ApiResponse.SUCCESS, "Find user by id", user);
				return ResponseEntity.ok(successResponse);
			} else {
				ApiResponse<User> successResponse = new ApiResponse<>(ApiResponse.NOT_FOUND, "User not found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(successResponse);
			}
		} catch (Exception e) {
			System.out.println(e);
			ApiResponse<Object> errorResponse = new ApiResponse<>(ApiResponse.ERROR, "Internal server error");
			return ResponseEntity.internalServerError().body(errorResponse);
		}
	}

	@PostMapping("/store")
	public ResponseEntity<Object> storeUser(@Valid @RequestBody StoreUserRequest storeUserRequest) {
		try {
			User user = new User(storeUserRequest.getUserName(), storeUserRequest.getEmail(), storeUserRequest.getPhoneNumber(), storeUserRequest.getPassword());

			User newUser = userService.addUser(user);
			ApiResponse<User> successResponse = new ApiResponse<User>(ApiResponse.SUCCESS, "Add new user", newUser);
			return ResponseEntity.ok(successResponse);
		} catch (Exception e) {
			System.out.println(e);
			ApiResponse<Object> errorResponse = new ApiResponse<>(ApiResponse.ERROR, "Internal server error");
			return ResponseEntity.internalServerError().body(errorResponse);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
		try {
			User userDetails = new User(updateUserRequest.getUserName(), updateUserRequest.getEmail(), updateUserRequest.getPhoneNumber(), updateUserRequest.getPassword());

			User updatedUser = userService.updateUser(id, userDetails);
			if (updatedUser == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(updatedUser);
			}
		} catch (Exception e) {
			System.out.println(e);
			ApiResponse<Object> errorResponse = new ApiResponse<>(ApiResponse.ERROR, "Internal server error");
			return ResponseEntity.internalServerError().body(errorResponse);
		}
	}

	@DeleteMapping("/destroy/{id}")
	public ResponseEntity<Object> destroyUser(@PathVariable Long id) {
		try {
			userService.destroyUser(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			System.out.println(e);
			ApiResponse<Object> errorResponse = new ApiResponse<>(ApiResponse.ERROR, "Internal server error");
			return ResponseEntity.internalServerError().body(errorResponse);
		}
	}
}
