package com.example.shoponline.service;

import com.example.shoponline.entity.Product;

import java.util.List;

/**
 * @author mangvientrieu
 */
public interface ProductService {
	List<? extends Product> getProducts(String type);
	<T extends Product> Boolean insertProduct(Object object, String type);
}
