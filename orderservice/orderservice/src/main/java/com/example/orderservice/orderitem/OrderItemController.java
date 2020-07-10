package com.example.orderservice.orderitem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderservice.orderitem.entity.OrderItem;
import com.example.orderservice.orderitem.globalexception.OrderItemNotFound;
import com.example.orderservice.orderitem.repository.OrderItemRepository;

@RestController
public class OrderItemController {
	
	@Autowired
	OrderItemRepository orderItemrepository;
	
	@PostMapping(path = "/orderitem",produces = "application/json",consumes = "application/json")
	OrderItem createOrderItem(@RequestBody OrderItem orderItem ) {
		OrderItem ordItem = null;
		try {
		ordItem =  orderItemrepository.save(orderItem);
		}catch(Exception e) {
			throw new OrderItemNotFound();
		}
		return ordItem;
		
	}
	
	@GetMapping(path = "/orderitems",produces ="application/json")
	public List<OrderItem> getAllOrderItemss(){
		List<OrderItem> ls = null;
		try {
		 ls = orderItemrepository.findAll();
		}catch(Exception e) {
			throw new OrderItemNotFound();
		}
		return ls;
		
	}
	
	
	@GetMapping(path = "/order/items/{id}",produces ="application/json")
	public List<Object> getAllOrderItems(@PathVariable Long id){
		List<Object> l = new ArrayList<>();
		try {
		List<OrderItem> ls = orderItemrepository.findAll();
		for(OrderItem o: ls) {
			 if(o.getProductCode().equals(id)) {
				 System.out.println("under if");
				 l.add(o.getProductName());
				 l.add(o.getProductQuantity());
			 }
		 }
		}catch(Exception e) {
			throw new OrderItemNotFound(id);
		}
		 
		return l;
	}
	
	
	
	
	@GetMapping(path = "/orderitems/{id}",produces ="application/json")
	public OrderItem getOrderItems(@PathVariable Long id){
		return orderItemrepository.findById(id).orElseThrow(()-> new OrderItemNotFound(id));
	}
	
	@DeleteMapping(path = "/orderitems/{id}")
	public void deleteById(@PathVariable Long id){
		try {
			orderItemrepository.deleteById(id);
		}catch(Exception e) {
			throw new OrderItemNotFound(id);
		}
	}

	@DeleteMapping(path = "/orderitems")
	public void deleteAll(){
		try {
			orderItemrepository.deleteAll();
		}catch(Exception e) {
			throw new OrderItemNotFound();
		}
	}
}
