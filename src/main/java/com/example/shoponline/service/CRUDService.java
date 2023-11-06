package com.example.shoponline.service;

import com.example.shoponline.entity.Product;

import java.util.List;

/**
 * @author mangvientrieu
 */
public interface CRUDService<T extends Product> {
	T create(T input);

	List<T> read();

	T update(T newValue);

	boolean delete(Long id);

}
