package com.example.order.order;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Service
@FeignClient(name = "OrderItemApplication",url = "localhost:8081")
public interface OrderItemProxy {
	
	@GetMapping(path = "/order/items/{id}",produces ="application/json")
	public List<Object> getAllOrderItems(@PathVariable Long id);

	

	

}
