package com.example.order.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.order.order.entity.Order;
import com.example.order.order.globalexception.OrderNotFound;
import com.example.order.order.repository.OrderRepository;



@RestController
public class OrderController {
	
	@Autowired
	OrderRepository orderepository; 
	
	@Autowired(required = true)
	OrderItemProxy orderItemProxy;
	
	@PostMapping(path = "/order",consumes = "application/json" , produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	 public Order createOrder(@RequestBody Order newOrder) {
		try {
		Long productid = newOrder.getProductCode();
		List<Object> ls =  orderItemProxy.getAllOrderItems(productid);
		newOrder.setProductName(ls.get(0).toString());
		newOrder.setProductQuantity((int) ls.get(1));
		}catch(Exception e) {
			throw new OrderNotFound();
		}
		return orderepository.save(newOrder) ;
	}
	

	@GetMapping(path = "/orders" , produces = "application/json" )
	  List<Order> allOrder() {
		List<Order> ls = null;
		try {
	     ls = orderepository.findAll();
		}catch(Exception e) {
			throw new OrderNotFound();
		}
		return ls;
	  }
	
	@GetMapping(path = "/order/{id}" , produces = "application/json" )
	  Order singleOrder(@PathVariable Long id) {
	    return orderepository.findById(id).orElseThrow(()-> new OrderNotFound(id));
	  }
	
	
	
	
	
	@PutMapping(path = "/order/{id}",produces = "application/json" ,consumes = "application/json" )
	public Order updateOrder(@RequestBody Order updatedOrder ,@PathVariable Long id) {
		
		
		Order order  = orderepository.getOne(id);
		try {
		if(isNotNullorEmpty(updatedOrder.getCustomerName()))
		order.setCustomerName(updatedOrder.getCustomerName());
		
		if(isNotNullorEmpty(updatedOrder.getOrderDate()))
			order.setOrderDate(updatedOrder.getOrderDate());
		
		if(isNotNullorEmpty(updatedOrder.getShippingAddress()))
			order.setShippingAddress(updatedOrder.getShippingAddress());
		
		if(updatedOrder.getTotalCost()>0)
		order.setTotalCost(updatedOrder.getTotalCost());
		
		if(updatedOrder.getProductCode() !=0) {
			order.setProductCode(updatedOrder.getProductCode());
		}
		}catch(OrderNotFound e) {
			throw new OrderNotFound();
		}catch(Exception e) {
			e.getMessage();
		}
		
		return orderepository.save(order);
		
		
	}
	
	
	@DeleteMapping(path = "/delorder/{id}")
	public void deleteOrder(@PathVariable Long id) {
		try {
		orderepository.deleteById(id);
		}catch(Exception e) {
			throw new OrderNotFound(id);
		}
		
	}
	
	@DeleteMapping(path = "/delorder")
	public void deleteAllOrder() {
		try {
		orderepository.deleteAll();
		}catch(Exception e) {
			throw new OrderNotFound();
		}
		
	}
	
	
	public static boolean isNotNullorEmpty(String s) {
		if(s == null)
			return false;
		else if(s.equals("")) {
			return false;
		}else {
			return true;
		}
		
	}
	

}
