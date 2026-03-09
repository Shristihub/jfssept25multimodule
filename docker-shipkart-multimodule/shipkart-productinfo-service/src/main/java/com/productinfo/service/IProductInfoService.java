package com.productinfo.service;

import java.util.List;

import com.productinfo.exception.ProductNotFoundException;
import com.productinfo.model.Product;
import com.productinfo.model.Type;

public interface IProductInfoService {
	
	List<Product> getAll();
	Type getById(int productId) throws ProductNotFoundException;
	
	
	List<Product> getByCategory(String category) throws ProductNotFoundException;
	List<Product> getByBrandAndPayType(String brand,String payment) throws ProductNotFoundException;
	List<Product> getByColor(String color) throws ProductNotFoundException;
	List<Product> getByNameContains(String name)throws ProductNotFoundException;

}
