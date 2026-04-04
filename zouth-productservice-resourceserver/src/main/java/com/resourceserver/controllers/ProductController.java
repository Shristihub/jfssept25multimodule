package com.resourceserver.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.resourceserver.model.Product;

@RestController
public class ProductController {

	@GetMapping("/products")
	List<Product> getAll() {
		return fetchProducts();
	}

	@GetMapping("/products/productId/{productId}")
	Product getById(@PathVariable int productId) {
		return fetchProducts().stream().filter(product -> product.getProductId() == productId).findFirst().get();
	}

	private List<Product> fetchProducts() {
		return Arrays.asList(
				new Product("Laptop", 1, 80000), 
				new Product("Pen", 2, 80),
				new Product("Camera", 3, 18000), 
				new Product("Hand Bag", 4, 2000)

		);
	}
}
