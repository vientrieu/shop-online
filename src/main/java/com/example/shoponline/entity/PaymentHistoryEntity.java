package com.example.shoponline.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author mangvientrieu
 */
@Getter
@Setter
@Entity
@Table(name = "payment_history")
public class PaymentHistoryEntity extends SuperEntity {
	private Long userId;
	private Long productId;
	private String category;
	private LocalDateTime soldDate;
	private BigDecimal price;
}
