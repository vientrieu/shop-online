package com.example.shoponline.dto.otp.input;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author mangvientrieu
 */
@Getter
@Setter
public class VerifyOTPInput implements Serializable {
	private static final long serialVersionUID = -7095781412376376360L;

	private String otpValue;
	private String otpToken;
}
