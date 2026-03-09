package com.productinfo.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productinfo.exception.ProductNotFoundException;
import com.productinfo.feign.IProductCatalogFeignClient;
import com.productinfo.model.Failure;
import com.productinfo.model.Product;
import com.productinfo.model.Type;
import com.productinfo.service.IProductInfoService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ProductInfoResilienceServiceImpl implements IProductInfoService {

	@Autowired
	private IProductCatalogFeignClient feignClient;

	@Override
	public List<Product> getAll() {
		return feignClient.viewAll();
	}

	@Override
	@CircuitBreaker(name = "infoService", fallbackMethod = "fallbackGetById")
	public Type getById(int productId) {
		System.out.println("in info--- "+productId);
		return feignClient.viewById(productId);
	}

	// like a catch - handling the exception
	public Type fallbackGetById(int productId,Exception e) {
		System.out.println(e.getMessage());
		return new Failure("Exception" +e.getMessage());
	}

	@Override
	@CircuitBreaker(name = "infoService", fallbackMethod = "fallbackGetByCat")
	public List<Product> getByCategory(String category) throws ProductNotFoundException {
		return feignClient.viewByCategory(category);
	}

	public List<Product> fallbackGetByCat(Exception e) {
		return Collections.emptyList();
	}

	@Override
	public List<Product> getByBrandAndPayType(String brand, String payment) throws ProductNotFoundException {
		return null;
//		return feignClient.viewByBrandAndPayType(brand, payment);
	}

	@Override
	public List<Product> getByColor(String color) throws ProductNotFoundException {
//		return feignClient.viewByColor(color);
		return null;
	}

	@Override
	public List<Product> getByNameContains(String name) throws ProductNotFoundException {
//		return feignClient.viewByNameContains(name);
		return null;
	}

}
