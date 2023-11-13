package com.example.shoponline.util;

import com.example.shoponline.exception.AuthException;
import com.example.shoponline.exception.BusinessException;
import com.google.common.hash.Hashing;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author mangvientrieu
 */
public final class OTPUtil {
	private OTPUtil() {
	}

	public static String generateOTPToken(String secretKey, String otpId) {
		Date now = new Date();
		return Jwts.builder()
				.setId(otpId)
				.setExpiration(new Date(now.getTime() + 30 * 1000))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public static Claims getClaimsOTP(String token, String secretKey) throws AuthException {
		try {
			return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			throw new BusinessException("OTP_EXPIRED", "OTP đã hết hạn");
		} catch (Exception e) {
			throw new BusinessException("OTP_INCORRECT", "OTP không chính xác");
		}
	}

	public static String generateOTPValue(String secretKey, String otpId, String userId) {
		long longToken = Hashing.hmacSha256(secretKey.getBytes(StandardCharsets.UTF_8))
				.hashString(otpId + userId, StandardCharsets.UTF_8)
				.asLong();
		long otp = Math.abs(longToken) % 1_000_000;
		return String.format("%06d", otp);
	}

	public static boolean verifyOTP(String secretKey, String otpId, String userId, String otpValue) {
		String oldOTP = generateOTPValue(secretKey, otpId, userId);
		return oldOTP.equals(otpValue);
	}
}
