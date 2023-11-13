package com.example.shoponline.service.impl;

import com.example.shoponline.entity.MobileEntity;
import com.example.shoponline.repository.MobileRepository;
import com.example.shoponline.service.MobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mangvientrieu
 */
@Service
@RequiredArgsConstructor
public class MobileServiceImpl implements MobileService {
	private final MobileRepository mobileRepository;

	@Override
	public MobileEntity create(MobileEntity input) {
		return mobileRepository.save(input);
	}

	@Override
	public List<MobileEntity> read() {
		return mobileRepository.findAll();
	}

	@Override
	public MobileEntity update(MobileEntity newValue) {
		return mobileRepository.save(newValue);
	}

	@Override
	public boolean delete(Long id) {
		mobileRepository.deleteById(id);
		return true;
	}
}
