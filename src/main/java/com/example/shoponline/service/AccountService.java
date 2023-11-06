package com.example.shoponline.service;

import com.example.shoponline.dto.account.input.ChangePasswordInput;
import com.example.shoponline.dto.account.input.UpdatePersonalInfoInput;
import com.example.shoponline.dto.account.output.GetPersonalInfoOutput;

/**
 * @author mangvientrieu
 */
public interface AccountService {
	GetPersonalInfoOutput getPersonalInfo();

	GetPersonalInfoOutput updatePersonalInfo(UpdatePersonalInfoInput input);

	Boolean changePassword(ChangePasswordInput input);
}
