package com.example.shoponline.util;

import com.example.shoponline.customenum.Category;
import com.example.shoponline.entity.LaptopEntity;
import com.example.shoponline.entity.MobileEntity;
import com.example.shoponline.entity.MonitorEntity;
import com.example.shoponline.entity.Product;
import com.example.shoponline.repository.LaptopRepository;
import com.example.shoponline.repository.MobileRepository;
import com.example.shoponline.repository.MonitorRepository;
import com.example.shoponline.service.CRUDService;
import com.example.shoponline.service.LaptopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author mangvientrieu
 */
@Component
@RequiredArgsConstructor
public class ProductFactory {
	private final LaptopRepository laptopRepository;
	private final MobileRepository mobileRepository;
	private final MonitorRepository monitorRepository;
	private final LaptopService laptopService;
	private final ObjectMapper objectMapper;

	public JpaRepository<? extends Product, Long> getRepository(Category category) {
		switch (category) {
			case LAPTOP: {
				return laptopRepository;
			}
			case MOBILE: {
				return mobileRepository;
			}
			case MONITOR: {
				return monitorRepository;
			}
			case RAM: {

			}
			default: {
				return null;
			}
		}
	}

	public Class<? extends Product> getClass(Category category) {
		switch (category) {
			case LAPTOP: {
				return LaptopEntity.class;
			}
			case MOBILE: {
				return MobileEntity.class;
			}
			case MONITOR: {
				return MonitorEntity.class;
			}
			default: {
				return null;
			}
		}
	}

	public Product getEntity(Object object, Category category) {
		switch (category) {
			case LAPTOP: {
				return objectMapper.convertValue(object, LaptopEntity.class);
			}
			case MOBILE: {
				return objectMapper.convertValue(object, MobileEntity.class);
			}
			case MONITOR: {
				return objectMapper.convertValue(object, MonitorEntity.class);
			}
			default: {
				return null;
			}
		}
	}

	public CRUDService<? extends Product> getService(Category category) {
		switch (category) {
			case LAPTOP: {
				return laptopService;
			}
			default: {
				return null;
			}
		}
	}

}
