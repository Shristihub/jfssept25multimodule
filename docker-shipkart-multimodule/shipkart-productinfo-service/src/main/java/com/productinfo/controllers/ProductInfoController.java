package com.productinfo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productinfo.model.Product;
import com.productinfo.model.Type;
import com.productinfo.service.IProductInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/info-service/v1")
@RequiredArgsConstructor
public class ProductInfoController {

	private final IProductInfoService productService;
	
	// http://localhost:8082/info-service/v1/products
	@GetMapping("/products")
	ResponseEntity<List<Product>> viewAll(){
		List<Product> products = productService.getAll();
	  	//wrap using responseentity
		return ResponseEntity.ok(products);
	}
	
	// http://localhost:8082/info-service/v1/products/productId/1
	@GetMapping("/products/productId/{productId}")
	ResponseEntity<Type> viewById(@PathVariable int productId) {
		Type result = productService.getById(productId);
		return ResponseEntity.ok(result);
	}
	
	// http://localhost:8082/info-service/v1/products/category/bags
	@GetMapping("/products/category/{category}")
	ResponseEntity<List<Product>> viewByCategory(@PathVariable String category) {
		List<Product> products = productService.getByCategory(category);
		return ResponseEntity.ok(products);
	}
	// http://localhost:8082/info-service/v1/products/brand/Samsung/pay/NO COST EMI
	@GetMapping("/products/brand/{brand}/pay/{payment}")
	ResponseEntity<List<Product>> viewByBrandAndPayType(@PathVariable String brand,@PathVariable String payment) {
		List<Product> products = productService.getByBrandAndPayType(brand, payment);
		return ResponseEntity.ok(products);
	}
	
	// http://localhost:8082/info-service/v1/products/color/red
	@GetMapping("/products/color/{color}")
	ResponseEntity<List<Product>> viewByColor(@PathVariable String color) {
		List<Product> products = productService.getByColor(color);
		return ResponseEntity.ok(products);
	}
	
	// http://localhost:8082/info-service/v1/products/product-name/shoe
	@GetMapping("/products/product-name/{name}")
	ResponseEntity<List<Product>> viewByNameContains(@PathVariable String name){
		List<Product> products = productService.getByNameContains(name);
		return ResponseEntity.ok(products);
	}

	
	
}







