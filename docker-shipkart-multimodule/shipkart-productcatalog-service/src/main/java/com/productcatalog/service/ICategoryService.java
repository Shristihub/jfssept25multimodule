package com.productcatalog.service;

import java.util.List;

import com.productcatalog.model.dtos.CategoryDto;

public interface ICategoryService {
	// crud operation
	void addCategory(CategoryDto categoryDto);
	void updateCategory(CategoryDto categoryDto);
	void deleteCategory(int categoryId);
	List<CategoryDto> getAll();
	CategoryDto getById(int categoryId);
	
	
	
	

}
