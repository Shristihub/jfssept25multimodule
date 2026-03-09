package com.productinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.productinfo.exception.ProductNotFoundException;
import com.productinfo.model.Product;
import com.productinfo.service.IProductInfoService;

//@Service
public class ProductInfoServiceImpl implements IProductInfoService {
	
	private RestClient restClient;
	
	public ProductInfoServiceImpl(@Qualifier("loadbalancedBuilder")  @Lazy RestClient.Builder restClientBuilder) {
		super();
		this.restClient = restClientBuilder.build();
	}

	//pass the uri fo the microservice
	String BASEURI = "http://product-catalog/catalog-service/v1/products";
	
	@Override
	public List<Product> getAll() {
		//	http://localhost:8081/catalog-service/v1/products
		return  restClient
		  .get()
		  .uri(BASEURI)
		  .retrieve()
		  .body(new ParameterizedTypeReference<>(){});
		
	}

	@Override
	public Product getById(int productId) throws ProductNotFoundException {
//		http://localhost:8081/product-api/v1/products/productId/1
		return restClient
				.get()
				.uri(BASEURI.concat("/productId/{productId}"), productId)
				.retrieve()
				.body(Product.class);
	}

	@Override
	public List<Product> getByCategory(String category) throws ProductNotFoundException {
//		http://localhost:8081/product-api/v1/products/category?categoryname=sports
		ResponseEntity<List<Product>> productresponse =  restClient
				  .get()
				  .uri(BASEURI.concat("/category?categoryname={category}"),category)
				  .retrieve()
				  .toEntity(new ParameterizedTypeReference<List<Product>>() {});
		//get the data from the body of the response body
		return productresponse.getBody();
	}

	@Override
	public List<Product> getByBrandAndPayType(String brand, String payment) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getByColor(String color) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getByNameContains(String name) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


	
}
