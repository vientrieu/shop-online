package com.example.shoponline.service;

import java.util.List;

/**
 * @author mangvientrieu
 */
public interface CartService {
	/**
	 * Add product to cart boolean.
	 *
	 * @param object the object
	 * @return the boolean
	 */
	Boolean addProductToCart(Object object);

	/**
	 * View product in cart boolean.
	 *
	 * @return the boolean
	 */
	List<Object> viewProductInCart();

	/**
	 * Add product to cart boolean.
	 *
	 * @param object the object
	 * @return the boolean
	 */
	Boolean removeProductFromCart(Object object);
}
