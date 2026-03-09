package com.productcatalog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.productcatalog.model.dtos.BrandDto;
import com.productcatalog.model.entities.Brand;
import com.productcatalog.repository.IBrandRepository;
import com.productcatalog.service.IBrandService;
import com.productcatalog.util.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements IBrandService{
	private final IBrandRepository brandRepository;
	private final ProductMapper productMapper;

	@Override
	public void addBrand(BrandDto brandDto) {
		Brand brand = productMapper.converttoBrandEntity(brandDto);
		brandRepository.save(brand);
		
	}

	@Override
	public void updateBrand(BrandDto brandDto) {
		Brand brand = productMapper.converttoBrandEntity(brandDto);
		brandRepository.save(brand);
	}

	@Override
	public void deleteBrand(int brandId) {
		brandRepository.deleteById(brandId);
		
	}

	@Override
	public List<BrandDto> getAll() {
		return null;
	}

	@Override
	public BrandDto getById(int brandId) {
		Optional<Brand> optbrand = brandRepository.findById(brandId);
		if(optbrand.isPresent()) {
			Brand brand = optbrand.get();
			return productMapper.converttoBrandDto(brand);
		}
		return null;
	}

}
