package com.example.orderservice.orderitem.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {
	
	@ResponseBody
	@ExceptionHandler(value = OrderItemNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String ItemNotFound(OrderItemNotFound e) {
		return e.getMessage();
	}

}
