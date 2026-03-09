package com.productinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productinfo.exception.ProductNotFoundException;
import com.productinfo.feign.IProductCatalogFeignClient;
import com.productinfo.model.Product;
import com.productinfo.service.IProductInfoService;

//@Service
public class ProductInfoFeignServiceImpl implements IProductInfoService {
	
	@Autowired
	private IProductCatalogFeignClient feignClient;

	@Override
	public List<Product> getAll() {
		return feignClient.viewAll();
	}

	@Override
	public Product getById(int productId) throws ProductNotFoundException {
		return feignClient.viewById(productId);
	}

	@Override
	public List<Product> getByCategory(String category) throws ProductNotFoundException {
		return feignClient.viewByCategory(category);
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
