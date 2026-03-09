package com.productinfo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.productinfo.model.Product;



@FeignClient(name = "product-catalog")
public interface IProductCatalogFeignClient {
	
//	http://localhost:8081/catalog-service/v1/products
	@GetMapping("/catalog-service/v1/products")
	List<Product> viewAll() ;

//	http://localhost:8081/catalog-service/v1/products/productId/1
	@GetMapping("/catalog-service/v1/products/productId/{productId}")
	Product viewById(@PathVariable int productId) ;
	
	
//	http://localhost:8081/catalog-service/v1/products/category?categoryname=sports
	@GetMapping("/catalog-service/v1/products/category")
	List<Product> viewByCategory(@RequestParam String categoryname) ;
	
//	http://localhost:8081/catalog-service/v1/products/brand/Samsung/payment/UPI
	@GetMapping("/products/brand/{brand}/payment/{payment}")
	List<Product> viewByBrandAndPayType(@PathVariable String brand,@PathVariable String payment) ;
	
//	http://localhost:8081/catalog-service/v1/products/color/red
	@GetMapping("/products/color/{color}")
	List<Product> viewByColor(@PathVariable String color) ;
	
//	http://localhost:8081/catalog-service/v1/products/name/bottle
	@GetMapping("/products/name/{name}")
	List<Product> viewByNameContains(@PathVariable String name);

}
