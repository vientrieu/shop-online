package com.example.shoponline.repository;

import com.example.shoponline.entity.LaptopEntity;
import com.example.shoponline.entity.MobileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mangvientrieu
 */
@Repository
public interface MobileRepository extends JpaRepository<MobileEntity, Long> {
}
