package com.productcatalog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.productcatalog.model.dtos.CategoryDto;
import com.productcatalog.model.entities.Category;
import com.productcatalog.repository.ICategoryRepository;
import com.productcatalog.service.ICategoryService;
import com.productcatalog.util.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{
	private final ICategoryRepository categoryRepository;
	private final ProductMapper productMapper;

	@Override
	public void addCategory(CategoryDto categoryDto) {
		Category category = productMapper.converttoCategoryEntity(categoryDto);
		categoryRepository.save(category);
		
	}

	@Override
	public void updateCategory(CategoryDto categoryDto) {
		Category category = productMapper.converttoCategoryEntity(categoryDto);
		categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(int categoryId) {
		categoryRepository.deleteById(categoryId);
		
	}

	@Override
	public List<CategoryDto> getAll() {
		List<Category> categories =  categoryRepository.findAll();
		return categories.stream()
				        .map(category->productMapper.converttoCategoryDto(category))
				        .toList();
	}

	@Override
	public CategoryDto getById(int categoryId) {
		Optional<Category> optcategory = categoryRepository.findById(categoryId);
		if(optcategory.isPresent()) {
			Category category = optcategory.get();
			return productMapper.converttoCategoryDto(category);
		}
		return null;
	}

}
