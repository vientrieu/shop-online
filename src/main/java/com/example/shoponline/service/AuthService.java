package com.example.shoponline.service;

import com.example.shoponline.dto.auth.input.LoginInput;
import com.example.shoponline.dto.auth.input.SignUpInput;
import com.example.shoponline.dto.auth.output.LoginOutput;
import com.example.shoponline.dto.auth.output.SignUpOutput;

/**
 * @author mangvientrieu
 */
public interface AuthService {
	LoginOutput login(LoginInput input);
	SignUpOutput signUp(SignUpInput input);
}
