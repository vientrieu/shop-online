package com.example.shoponline.dto.otp.output;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author mangvientrieu
 */
@Getter
@Setter
public class InitOTPOutput implements Serializable {
	private static final long serialVersionUID = -5500208253568134392L;

	private String otpToken;
}
