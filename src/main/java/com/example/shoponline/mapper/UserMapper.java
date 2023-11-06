package com.example.shoponline.mapper;

import com.example.shoponline.dto.account.output.GetPersonalInfoOutput;
import com.example.shoponline.dto.auth.input.SignUpInput;
import com.example.shoponline.entity.UserEntity;
import com.example.shoponline.util.AuthUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author mangvientrieu
 */
@Mapper(imports = AuthUtil.class)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "password", expression = "java(AuthUtil.hashPassword(input.getPassword()))")
	UserEntity mapFromSignUpInput(SignUpInput input);

	GetPersonalInfoOutput mapToPersonalInfoOutput(UserEntity input);
}
