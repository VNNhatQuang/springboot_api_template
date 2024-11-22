package com.example.springboot_api_template.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot_api_template.model.User;
import com.example.springboot_api_template.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Lấy toàn bộ danh sách user
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Tìm user dựa vào Id
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	/**
	 * Thêm mới user
	 * 
	 * @param user
	 * @return
	 */
	public User addUser(User user) {
		Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
		if (existingUser.isPresent()) {
			throw new RuntimeException("User Name already exists!");
		}
		User newUser = new User(user.getUserName(), user.getEmail(), user.getPhoneNumber(), user.getPassword(), Date.from(Instant.now()), Date.from(Instant.now()));
		return userRepository.save(newUser);
	}

	/**
	 * Chỉnh sửa thông tin user
	 * 
	 * @param id
	 * @param userDetails
	 * @return
	 */
	public User updateUser(Long id, User userDetails) {
		User user = getUserById(id);
		if (user != null) {
			user.setUserName(userDetails.getUserName());
			user.setEmail(userDetails.getEmail());
			user.setPhoneNumber(userDetails.getPhoneNumber());
			user.setPassword(userDetails.getPassword());
//			user.setCode(userDetails.getCode());
//			user.setCodeExpiredAt(userDetails.getCodeExpiredAt());
			user.setUpdatedAt(Date.from(Instant.now()));
//			user.setCreatedAt(userDetails.getCreatedAt());
			return userRepository.save(user);
		}
		return null;
	}

	/**
	 * Xóa user
	 * 
	 * @param id
	 */
	public void destroyUser(Long id) {
		userRepository.deleteById(id);
	}

	/**
	 * Tìm user dựa vào userName
	 * 
	 * @param userName
	 * @return
	 */
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName).orElse(null);
	}

}
