package com.example.order.order.globalexception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class OrderNotFounderException {
	
	@ResponseBody
	@ExceptionHandler(value = OrderNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String orderNotFounderException(OrderNotFound ex) {
		return ex.getMessage();
		
	}

}
