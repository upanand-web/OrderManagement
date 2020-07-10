package com.example.order.order.globalexception;

@SuppressWarnings("serial")
public class OrderNotFound extends RuntimeException {
	
	public OrderNotFound() {
		
	}
	
	public OrderNotFound(Long id) {
		super("Order not Found "+id);
		
	}

}
