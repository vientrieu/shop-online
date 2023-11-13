package com.example.shoponline.service.impl;

import com.example.shoponline.entity.MonitorEntity;
import com.example.shoponline.repository.MonitorRepository;
import com.example.shoponline.service.MonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mangvientrieu
 */
@Service
@RequiredArgsConstructor
public class MonitorServiceImpl implements MonitorService {
	private final MonitorRepository monitorRepository;

	@Override
	public MonitorEntity create(MonitorEntity input) {
		return monitorRepository.save(input);
	}

	@Override
	public List<MonitorEntity> read() {
		return monitorRepository.findAll();
	}

	@Override
	public MonitorEntity update(MonitorEntity newValue) {
		return monitorRepository.save(newValue);
	}

	@Override
	public boolean delete(Long id) {
		monitorRepository.deleteById(id);
		return true;
	}
}
