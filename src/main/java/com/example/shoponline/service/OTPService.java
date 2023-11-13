package com.example.shoponline.service;

import com.example.shoponline.dto.otp.input.VerifyOTPInput;
import com.example.shoponline.dto.otp.output.InitOTPOutput;

/**
 * @author mangvientrieu
 */
public interface OTPService {
	InitOTPOutput generateOTP(String type);

	boolean verifyOTP(VerifyOTPInput input);

}
