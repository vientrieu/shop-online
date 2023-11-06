package com.example.shoponline.controller;

import com.example.shoponline.dto.api.ResponseDto;
import com.example.shoponline.dto.auth.input.LoginInput;
import com.example.shoponline.dto.auth.input.SignUpInput;
import com.example.shoponline.dto.auth.output.LoginOutput;
import com.example.shoponline.dto.auth.output.SignUpOutput;
import com.example.shoponline.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mangvientrieu
 * Controller dùng để quản lý việc đăng nhập, đăng kí, các api liên quan đến authen/author
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseDto<LoginOutput> login(@RequestBody LoginInput input) {
		return ResponseDto.ok(authService.login(input));
	}

	@PostMapping("/sign-up")
	public ResponseDto<SignUpOutput> signUp(@RequestBody SignUpInput input) {
		return ResponseDto.ok(authService.signUp(input));
	}

}
