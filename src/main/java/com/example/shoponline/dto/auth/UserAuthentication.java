package com.example.shoponline.dto.auth;

import com.example.shoponline.customenum.RoleEnum;
import com.example.shoponline.customenum.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mangvientrieu
 */
@Getter
@Setter
public class UserAuthentication {
	private Long userId;
	private RoleEnum role;
	private UserTypeEnum type;
}
