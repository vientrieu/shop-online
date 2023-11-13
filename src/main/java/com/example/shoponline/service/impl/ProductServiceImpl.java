package com.example.shoponline.service.impl;

import com.example.shoponline.customenum.Category;
import com.example.shoponline.entity.Product;
import com.example.shoponline.service.ProductService;
import com.example.shoponline.util.ProductFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mangvientrieu
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductFactory productFactory;

	@Override
	public List<? extends Product> getProducts(String type) {
		Category category = Category.valueOf(type);
		JpaRepository<? extends Product, Long> repository = productFactory.getRepository(category);
		return repository.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertProduct(Object object, String type) {
		Category category = Category.valueOf(type);
		JpaRepository repository = productFactory.getRepository(category);
		Product product = productFactory.getEntity(object, category);
		repository.save(product);
		return true;
	}

}
