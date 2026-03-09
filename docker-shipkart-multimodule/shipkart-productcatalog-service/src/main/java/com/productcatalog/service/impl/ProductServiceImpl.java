package com.productcatalog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productcatalog.exception.ProductNotFoundException;
import com.productcatalog.feign.IInventoryFeignClient;
import com.productcatalog.model.dtos.ProductDto;
import com.productcatalog.model.entities.Product;
import com.productcatalog.repository.IProductRepository;
import com.productcatalog.service.IProductService;
import com.productcatalog.util.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements IProductService {

	private final IProductRepository productRepository;
	private final ProductMapper productMapper;
	private final IInventoryFeignClient feignClient;

	@Override
	public void addProduct(ProductDto productDto) {
		Product product = productMapper.converttoEntity(productDto);
		Product savedProduct = productRepository.save(product);
		int productId = savedProduct.getProductId();
		int stock = productDto.getStock();
        String status = feignClient.addStock(productId, stock);
        System.out.println(status);
	}

	@Override
	public void updateProduct(ProductDto productDto) {
		Product product = productMapper.converttoEntity(productDto);
		// if id available - update else insert
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(int productId) {
		productRepository.deleteById(productId);

	}

	@Override
	
	public List<ProductDto> getAll() {
		List<Product> products = productRepository.findAll();
		System.out.println(products);
		return products.stream().map(product -> productMapper.converttoDto(product)).toList();
		// check if empty throw exception
		// sort by name and return it
//		return null;
	}

	@Transactional
	@Override
	public ProductDto getById(int productId) throws ProductNotFoundException {
		Optional<Product> productOpt = productRepository.findById(productId);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			return productMapper.converttoDto(product);
		}else
		 throw new ProductNotFoundException("invalid id");
	}

	@Override
	public List<ProductDto> getByCategory(String category) {
		List<Product> products = productRepository.findByCategory(category);
		System.out.println(products);
		if(products.isEmpty())
			throw new ProductNotFoundException("product with this category not found");
		return products.stream().map(product -> productMapper.converttoDto(product)).toList();
	}

	@Override
	public List<ProductDto> getByBrandAndPayType(String brand, String payment) {
		List<Product> products = productRepository.findByBrandAndPayType(brand, payment);
		System.out.println(products);
		if(products.isEmpty())
			throw new ProductNotFoundException("product with this category not found");
		return products.stream().map(product -> productMapper.converttoDto(product)).toList();
	}
	@Override
	public List<ProductDto> getByColor(String color) {
		List<Product> products = productRepository.findByColor(color);
		System.out.println(products);
		if(products.isEmpty())
			throw new ProductNotFoundException("product with this category not found");
		return products.stream().map(product -> productMapper.converttoDto(product)).toList();
	}

	@Override
	public List<ProductDto> getByCategoryAndDelivery(String category, String delivery) {
		List<Product> products = productRepository.findByCategoryAndDelivery(category,delivery);
		System.out.println(products);
		if(products.isEmpty())
			throw new ProductNotFoundException("product with this category not found");
		return products.stream().map(product -> productMapper.converttoDto(product)).toList();
	}

	@Override
	public List<ProductDto> getByNameContains(String choice) {
		List<Product> products = productRepository.findByNameContains("%"+choice+"%");
		System.out.println(products);
		if(products.isEmpty())
			throw new ProductNotFoundException("product with this category not found");
		return products.stream().map(product -> productMapper.converttoDto(product)).toList();
	}

	@Override
	public List<ProductDto> getByNameOffers(String name, String offers) {
		List<Product> products = productRepository.findByNameOffers(name, offers);
		System.out.println(products);
		if(products.isEmpty())
			throw new ProductNotFoundException("product with this category not found");
		return products.stream().map(product -> productMapper.converttoDto(product)).toList();
	}

}
