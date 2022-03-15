package br.com.product.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private String date;
	
		
	
}