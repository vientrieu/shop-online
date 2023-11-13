package com.example.shoponline.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.UUID;

/**
 * @author mangvientrieu
 */
@RunWith(JUnit4.class)
public class OTPUtilTest {

	@Test
	public void generateOTPValue() {
		String secretKey = "thIsIsSecRetKeY";
		for (int i = 0; i < 10000; i++) {
			String string = OTPUtil.generateOTPValue(secretKey, UUID.randomUUID().toString());
			System.out.println(string + "============" + string.length());
		}
	}
}