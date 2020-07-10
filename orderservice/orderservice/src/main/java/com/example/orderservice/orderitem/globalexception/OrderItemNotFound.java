package com.example.orderservice.orderitem.globalexception;


public class OrderItemNotFound extends RuntimeException {
	
	
	
	public OrderItemNotFound(Long id) {
	super("Order item not found "+ id);
	}

	

	public OrderItemNotFound() {
		// TODO Auto-generated constructor stub
	}

}
