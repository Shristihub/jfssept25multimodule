package com.productcatalog.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-inventory", url="http://product-inventory:8083") //service name in docker file
public interface IInventoryFeignClient {
	
	@PostMapping("/inventory-service/v1/inventories/add-stock")  
	String addStock(@RequestParam int productId,@RequestParam int stock);
		
 }
