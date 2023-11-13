package com.example.shoponline.service.impl;

import com.example.shoponline.dto.otp.input.VerifyOTPInput;
import com.example.shoponline.dto.otp.output.InitOTPOutput;
import com.example.shoponline.exception.BusinessException;
import com.example.shoponline.service.OTPService;
import com.example.shoponline.util.AuthUtil;
import com.example.shoponline.util.OTPUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author mangvientrieu
 */
@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {
	private final RedisTemplate<String, Object> redisTemplate;
	@Value("${app.secret-key}")
	private String secretKey;

	@Override
	public InitOTPOutput generateOTP(String type) {
		String userId = AuthUtil.getUserId().toString();
		String otpId = UUID.randomUUID().toString().concat("_").concat(type);

		String otp = OTPUtil.generateOTPValue(secretKey, otpId, userId);
		InitOTPOutput output = new InitOTPOutput();
		output.setOtpToken(OTPUtil.generateOTPToken(secretKey, otpId));
		redisTemplate.boundValueOps(otpId).set(false, 30_000, TimeUnit.MILLISECONDS);
		System.out.println("Your OTP for " + type + " is " + otp); // Thay thế bằng việc gửi sms

		return output;
	}

	@Override
	public boolean verifyOTP(VerifyOTPInput input) {
		String userId = AuthUtil.getUserId().toString();
		String otpValue = input.getOtpValue();
		String otpToken = input.getOtpToken();

		Claims claimsOTP = OTPUtil.getClaimsOTP(otpToken, secretKey);
		String otpId = claimsOTP.getId();

		boolean result = OTPUtil.verifyOTP(secretKey, otpId, userId, otpValue);
		if (!result) {
			throw new BusinessException("OTP_INCORRECT", "OTP không chính xác");
		}
		Boolean cached = (Boolean) redisTemplate.boundValueOps(otpId).get();
		if (Boolean.FALSE.equals(cached)) {
			redisTemplate.boundValueOps(otpId).set(true, 30_000, TimeUnit.MILLISECONDS);
			return true;
		} else {
			throw new BusinessException("OTP_USED", "OTP đã được sử dụng");
		}
	}
}
