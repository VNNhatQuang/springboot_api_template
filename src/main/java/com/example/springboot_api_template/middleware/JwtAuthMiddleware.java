package com.example.springboot_api_template.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.springboot_api_template.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthMiddleware implements HandlerInterceptor {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader("Authorization");

		// Kiểm tra xem token có tồn tại và có đúng định dạng không
		if (token == null || !token.startsWith("Bearer ")) {
			throw new SignatureException("Missing or invalid Authorization header");
		}

		try {
			token = token.substring(7); // Bỏ tiền tố "Bearer "
			String userName = jwtUtil.extractToken(token); // Giải mã token để lấy thông tin người dùng

			request.setAttribute("userName", userName); // Lưu thông tin người dùng vào request để các API khác sử dụng
			return true; // Cho phép request tiếp tục

		} catch (ExpiredJwtException | SignatureException e) {
			throw e; // Ném lỗi để GlobalExceptionHandler xử lý
		} catch (Exception e) {
			throw new Exception("Lỗi xác thực token");
		}
	}
}
