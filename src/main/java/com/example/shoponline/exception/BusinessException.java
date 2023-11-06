package com.example.shoponline.exception;

import lombok.Getter;

/**
 * @author mangvientrieu
 */
@Getter
public class BusinessException extends RuntimeException {
	private final String errorCode;
	private final String errorMessage;

	public BusinessException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
