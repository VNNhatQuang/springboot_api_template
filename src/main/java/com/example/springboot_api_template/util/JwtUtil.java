package com.example.springboot_api_template.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt.secret_key}")
	private String SECRET_KEY;

	/**
	 * Tạo token dựa vào userName Token có hạn sử dụng là 10 tiếng
	 * 
	 * @param userName
	 * @return
	 */
	public String generateToken(String userName) {
		return Jwts.builder().setSubject(userName).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 giờ
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	/**
	 * Hàm mã hóa ngược token thành chuỗi ban đầu
	 * 
	 * @param token
	 * @return
	 */
	public String extractToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Hàm kiểm tra xem token đã hết hạn chưa
	 * 
	 * @param token
	 * @return
	 */
	public Boolean isTokenExpired(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date());
	}
}
