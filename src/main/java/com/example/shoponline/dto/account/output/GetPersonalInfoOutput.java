package com.example.shoponline.dto.account.output;

import com.example.shoponline.customenum.RoleEnum;
import com.example.shoponline.customenum.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author mangvientrieu
 */
@Getter
@Setter
public class GetPersonalInfoOutput {
	private String id;
	private String username;
	private String name;
	private UserTypeEnum type;
	private LocalDate birthday;
	private String cardIdNumber;
	private String phoneNumber;
	private RoleEnum role;
}
