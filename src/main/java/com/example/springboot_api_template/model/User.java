package com.example.springboot_api_template.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_name", unique = true, nullable = false)
	private String userName;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "phone_number", unique = true, nullable = false)
	private String phoneNumber;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "code")
	private Long code;

	@Column(name = "code_expired_at")
	private Date codeExpiredAt;

	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	public User() {
		super();
	}

	public User(long id, String userName, String email, String phoneNumber, String password, Long code, Date codeExpiredAt, Date updatedAt, Date createdAt) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.code = code;
		this.codeExpiredAt = codeExpiredAt;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}

	public User(String userName, String email, String phoneNumber, String password, Date updatedAt, Date createdAt) {
		super();
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}

	public User(String userName, String email, String phoneNumber, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Date getCodeExpiredAt() {
		return codeExpiredAt;
	}

	public void setCodeExpiredAt(Date codeExpiredAt) {
		this.codeExpiredAt = codeExpiredAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
