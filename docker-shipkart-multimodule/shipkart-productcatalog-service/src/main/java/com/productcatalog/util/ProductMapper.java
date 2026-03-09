package com.productcatalog.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.productcatalog.model.dtos.BrandDto;
import com.productcatalog.model.dtos.CategoryDto;
import com.productcatalog.model.dtos.ProductDto;
import com.productcatalog.model.entities.Brand;
import com.productcatalog.model.entities.Category;
import com.productcatalog.model.entities.Product;

@Component
public class ProductMapper {
	
	@Autowired
	private ModelMapper mapper;

	//product
	public ProductDto converttoDto(Product product){
		return mapper.map(product, ProductDto.class);
	}
	
	public Product converttoEntity(ProductDto productDto){
		return mapper.map(productDto, Product.class);
	}
	
	//category
	public Category converttoCategoryEntity(CategoryDto categoryDto){
		return mapper.map(categoryDto,Category.class);
	}
	
	public CategoryDto converttoCategoryDto(Category category){
		return mapper.map(category,CategoryDto.class);
	}

	//brand
	public Brand converttoBrandEntity(BrandDto brandDto) {
		return mapper.map(brandDto,Brand.class);
	}

	public BrandDto converttoBrandDto(Brand brand) {
		return mapper.map(brand, BrandDto.class);
	}
}
