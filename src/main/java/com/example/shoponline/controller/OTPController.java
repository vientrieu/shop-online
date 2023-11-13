package com.example.shoponline.controller;

import com.example.shoponline.dto.api.ResponseDto;
import com.example.shoponline.dto.otp.input.VerifyOTPInput;
import com.example.shoponline.dto.otp.output.InitOTPOutput;
import com.example.shoponline.service.OTPService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mangvientrieu
 */
@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OTPController {
	private final OTPService otpService;

	@GetMapping("/generate/{type}")
	public ResponseDto<InitOTPOutput> generateOTP(@PathVariable String type) {
		return ResponseDto.ok(otpService.generateOTP(type));
	}

	@PostMapping("/verify")
	public ResponseDto<Boolean> verifyOTP(@RequestBody VerifyOTPInput input) {
		return ResponseDto.ok(otpService.verifyOTP(input));
	}
}
