package com.example.orderservice.orderitem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {
	
	@Id 
	@GeneratedValue
	Long productCode;
	String productName;
	int productQuantity;
	
	public OrderItem() {}
	
	public OrderItem(Long productCode, String productName, int productQuantity) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productQuantity = productQuantity;
	}

	public Long getProductCode() {
		return productCode;
	}

	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductname(String productname) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	
	
	

}
