package com.example.shoponline.repository;

import com.example.shoponline.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findFirstByUsername(String username);

	UserEntity findFirstByCardIdNumber(String cardIdNumber);

	UserEntity findFirstByPhoneNumber(String phoneNumber);
}