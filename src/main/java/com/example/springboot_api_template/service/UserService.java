package com.example.springboot_api_template.service;

import java.util.List;

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
		return userRepository.save(user);
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
			user.setCode(userDetails.getCode());
			user.setCodeExpiredAt(userDetails.getCodeExpiredAt());
			user.setUpdatedAt(userDetails.getUpdatedAt());
			user.setCreatedAt(userDetails.getCreatedAt());
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
