package com.productcatalog.service;

import java.util.List;

import com.productcatalog.exception.ProductNotFoundException;
import com.productcatalog.model.dtos.ProductDto;

public interface IProductService {
	// crud operation
	void addProduct(ProductDto productDto);
	void updateProduct(ProductDto productDto);
	void deleteProduct(int productId);
	List<ProductDto> getAll();
	ProductDto getById(int productId) throws ProductNotFoundException;
	
	
	List<ProductDto> getByCategory(String category) throws ProductNotFoundException;
	List<ProductDto> getByBrandAndPayType(String brand,String payment) throws ProductNotFoundException;
	List<ProductDto> getByColor(String color) throws ProductNotFoundException;
	List<ProductDto> getByCategoryAndDelivery(String category,String delivery)throws ProductNotFoundException;
	List<ProductDto> getByNameContains(String name)throws ProductNotFoundException;
	List<ProductDto> getByNameOffers(String name,String offers)throws ProductNotFoundException;

}
