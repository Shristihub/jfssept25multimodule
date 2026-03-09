package com.productcatalog.service;

import java.util.List;

import com.productcatalog.model.dtos.BrandDto;

public interface IBrandService {
	// crud operation
	void addBrand(BrandDto brandDto);
	void updateBrand(BrandDto brandDto);
	void deleteBrand(int brandId);
	List<BrandDto> getAll();
	BrandDto getById(int brandId);
	
	
	
	

}
