package com.productcatalog.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalog.model.dtos.BrandDto;
import com.productcatalog.service.IBrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product-api/v1")
@RequiredArgsConstructor
public class BrandController {

	private final IBrandService brandService;

	// http://localhost:8081/product-api/v1/brands
	@PostMapping( "/brands")
	ResponseEntity<Void> addBrand(@RequestBody BrandDto brandDto) {
		brandService.addBrand(brandDto);
		return ResponseEntity.status(201).build();
	}

	// http://localhost:8081/product-api/v1/brands
	@PutMapping("/brands")
	ResponseEntity<Void>  updateBrand(@RequestBody BrandDto brandDto) {
		brandService.updateBrand(brandDto);
		return ResponseEntity.accepted().build();
	}

//	http://localhost:8081/product-api/v1/brands/brandId/1
	@DeleteMapping("/brands/brandId/{brandId}")
	ResponseEntity<Void>  deleteBrand(@PathVariable int brandId) {
		HttpHeaders headers =  new HttpHeaders();
		headers.add("info", "delete one brand");
		headers.add("timestamp", LocalTime.now().toString());
		brandService.deleteBrand(brandId);
		return ResponseEntity.ok().build();
	}

//	http://localhost:8081/product-api/v1/brands
	@GetMapping("/brands")
	ResponseEntity<List<BrandDto>> getAll() {
		List<BrandDto> brands = brandService.getAll();
		HttpHeaders headers =  new HttpHeaders();
		headers.add("info", "returns a list of brands");
		headers.add("timestamp", LocalTime.now().toString());
		// create a ReponseEntity Object and return it
		return ResponseEntity.ok().headers(headers).body(brands);
	}

//	http://localhost:8081/product-api/v1/brands/brandId/1
	@GetMapping("/brands/brandId/{brandId}")
	ResponseEntity<BrandDto> getById(@PathVariable int brandId) {
		HttpHeaders headers =  new HttpHeaders();
		headers.add("info", "returns one brand by id");
		BrandDto brandDto =  brandService.getById(brandId);
		return ResponseEntity.ok().headers(headers).body(brandDto);
	}
}







