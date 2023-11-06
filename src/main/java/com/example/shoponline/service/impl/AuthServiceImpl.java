package com.example.shoponline.service.impl;

import com.example.shoponline.customenum.RoleEnum;
import com.example.shoponline.customenum.UserTypeEnum;
import com.example.shoponline.dto.auth.UserAuthentication;
import com.example.shoponline.dto.auth.input.LoginInput;
import com.example.shoponline.dto.auth.input.SignUpInput;
import com.example.shoponline.dto.auth.output.LoginOutput;
import com.example.shoponline.dto.auth.output.SignUpOutput;
import com.example.shoponline.entity.UserEntity;
import com.example.shoponline.exception.BusinessException;
import com.example.shoponline.mapper.UserMapper;
import com.example.shoponline.repository.UserRepository;
import com.example.shoponline.service.AuthService;
import com.example.shoponline.util.AuthUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author mangvientrieu
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Value("${app.secret-key}")
	private String secretKey;

	@Override
	public LoginOutput login(LoginInput input) {
		Object test = redisTemplate.opsForValue().get("test");
		if (test != null) return (LoginOutput) test;
		// Kiểm tra username có tồn tại chưa
		String username = input.getUsername();
		UserEntity existedUser = userRepository.findFirstByUsername(username);
		if (existedUser == null) {
			throw new BusinessException("NOT_EXISTED_USERNAME", "Tài khoản chưa tồn tại!");
		}
		String hashedPassword = AuthUtil.hashPassword(input.getPassword());
		String savedPassword = existedUser.getPassword();
		if (!hashedPassword.equals(savedPassword)) {
			throw new BusinessException("INCORRECT_PASSWORD", "Mật khẩu nhập không chính xác!");
		}
		// Generate jwt(json web token) and return
		UserAuthentication userAuthentication = new UserAuthentication();
		userAuthentication.setUserId(existedUser.getId());
		userAuthentication.setRole(existedUser.getRole());
		userAuthentication.setType(existedUser.getType());
		Map<String, Object> payload = objectMapper.convertValue(userAuthentication, new TypeReference<>() {
		});
		LoginOutput output = new LoginOutput();
		output.setUserId(existedUser.getId());
		output.setToken(AuthUtil.generateToken(payload, secretKey));
		redisTemplate.delete("test");
		return output;
	}

	@Override
	public SignUpOutput signUp(SignUpInput input) {
		// Kiểm tra username có tồn tại chưa
		String username = input.getUsername();
		UserEntity existedUsername = userRepository.findFirstByUsername(username);
		if (existedUsername != null) {
			throw new BusinessException("EXISTED_USERNAME", "Tài khoản đã tồn tại!");
		}
		String cardIdNumber = input.getCardIdNumber();
		UserEntity existedCardIdNumber = userRepository.findFirstByCardIdNumber(cardIdNumber);
		if (existedCardIdNumber != null) {
			throw new BusinessException("EXISTED_CARD_ID_NUMBER", "Giấy tờ tùy thân đã tồn tại!");
		}
		String phoneNumber = input.getPhoneNumber();
		UserEntity existedPhoneNumber = userRepository.findFirstByPhoneNumber(phoneNumber);
		if (existedPhoneNumber != null) {
			throw new BusinessException("EXISTED_PHONE_NUMBER", "Số điện thoại đã tồn tại!");
		}
		UserEntity newUser = UserMapper.INSTANCE.mapFromSignUpInput(input);
		newUser.setRole(RoleEnum.USER);
		newUser.setType(UserTypeEnum.BASIC);
		userRepository.save(newUser);

		return new SignUpOutput(newUser.getId());
	}
}
