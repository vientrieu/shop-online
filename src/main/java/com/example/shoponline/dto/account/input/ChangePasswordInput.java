package com.example.shoponline.dto.account.input;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mangvientrieu
 */
@Getter
@Setter
public class ChangePasswordInput {
	private String oldPassword;
	private String newPassword;
}
