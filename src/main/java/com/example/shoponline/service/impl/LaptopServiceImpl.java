package com.example.shoponline.service.impl;

import com.example.shoponline.entity.LaptopEntity;
import com.example.shoponline.repository.LaptopRepository;
import com.example.shoponline.service.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mangvientrieu
 */
@Service
@RequiredArgsConstructor
public class LaptopServiceImpl implements LaptopService {
	private final LaptopRepository laptopRepository;
	@Override
	public LaptopEntity create(LaptopEntity input) {
		return laptopRepository.save(input);
	}

	@Override
	public List<LaptopEntity> read() {
		return laptopRepository.findAll();
	}

	@Override
	public LaptopEntity update(LaptopEntity newValue) {
		return laptopRepository.save(newValue);
	}

	@Override
	public boolean delete(Long id) {
		laptopRepository.deleteById(id);
		return true;
	}
}
