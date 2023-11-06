package com.example.shoponline.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author mangvientrieu
 */
@Getter
@Setter
@Entity
@Table(name = "monitor")
public class MonitorEntity extends Product {
	private String name;
	private String shortDescription;
	@Column(columnDefinition = "text")
	private String fullDescription;
	private String brand;
	private String color;
	private String resolution;
	private Double size;
	private BigDecimal price;
}
