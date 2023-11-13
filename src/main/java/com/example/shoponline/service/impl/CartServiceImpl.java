package com.example.shoponline.service.impl;

import com.example.shoponline.service.CartService;
import com.example.shoponline.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mangvientrieu
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	private final RedisTemplate<String, Object> redisTemplate;

	@Override
	public Boolean addProductToCart(Object object) {
		String userId = AuthUtil.getUserId().toString();
		String cartRedisKey = userId.concat("_CART");
		redisTemplate.boundListOps(cartRedisKey).rightPush(object);
		return true;
	}

	@Override
	public List<Object> viewProductInCart() {
		String userId = AuthUtil.getUserId().toString();
		String cartRedisKey = userId.concat("_CART");
		BoundListOperations<String, Object> result = redisTemplate.boundListOps(cartRedisKey);
		return result.range(0, result.size());
	}

	@Override
	public Boolean removeProductFromCart(Object object) {
		String userId = AuthUtil.getUserId().toString();
		String cartRedisKey = userId.concat("_CART");
		redisTemplate.boundListOps(cartRedisKey).remove(1, object);
		return true;
	}
}
