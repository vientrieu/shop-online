package com.example.shoponline.service.impl;

import com.example.shoponline.dto.account.input.ChangePasswordInput;
import com.example.shoponline.dto.account.input.UpdatePersonalInfoInput;
import com.example.shoponline.dto.account.output.GetPersonalInfoOutput;
import com.example.shoponline.entity.UserEntity;
import com.example.shoponline.exception.BusinessException;
import com.example.shoponline.mapper.UserMapper;
import com.example.shoponline.repository.UserRepository;
import com.example.shoponline.service.AccountService;
import com.example.shoponline.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author mangvientrieu
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public GetPersonalInfoOutput getPersonalInfo() {
		UserEntity userFound = userRepository.findById(AuthUtil.getUserId())
				.orElseThrow(() -> new BusinessException("USER_NOT_FOUND", "Thông tin cá nhân không được tìm thấy"));

		return UserMapper.INSTANCE.mapToPersonalInfoOutput(userFound);
	}

	@Override
	public GetPersonalInfoOutput updatePersonalInfo(UpdatePersonalInfoInput input) {
		UserEntity userFound = userRepository.findById(AuthUtil.getUserId())
				.orElseThrow(() -> new BusinessException("USER_NOT_FOUND", "Thông tin cá nhân không được tìm thấy"));
		Optional.ofNullable(input.getName()).ifPresent(userFound::setName);
		Optional.ofNullable(input.getBirthday()).ifPresent(userFound::setBirthday);
		Optional.ofNullable(input.getPhoneNumber()).ifPresent(userFound::setPhoneNumber);
		Optional.ofNullable(input.getCardIdNumber()).ifPresent(userFound::setCardIdNumber);

		return UserMapper.INSTANCE.mapToPersonalInfoOutput(userRepository.save(userFound));
	}

	@Override
	public Boolean changePassword(ChangePasswordInput input) {
		UserEntity userFound = userRepository.findById(AuthUtil.getUserId())
				.orElseThrow(() -> new BusinessException("USER_NOT_FOUND", "Thông tin cá nhân không được tìm thấy"));
		String hashedOldPassword = AuthUtil.hashPassword(input.getOldPassword());
		String existedPassword = userFound.getPassword();
		if (!hashedOldPassword.equals(existedPassword)) {
			throw new BusinessException("INCORRECT_PASSWORD", "Mật khẩu nhập không chính xác!");
		}
		String hashedNewPassword = AuthUtil.hashPassword(input.getNewPassword());
		if (hashedNewPassword.equals(existedPassword)) {
			throw new BusinessException("NEW_PASSWORD_MUST_DIFFERENT_OLD_PASSWORD",
					"Mật khẩu mới phải khác mật khẩu cũ!");
		}
		userFound.setPassword(hashedNewPassword);
		userRepository.save(userFound);

		return true;
	}
}
