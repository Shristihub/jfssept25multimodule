package com.productcatalog.controllers;

import java.util.List;

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

import com.productcatalog.model.dtos.CategoryDto;
import com.productcatalog.service.ICategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product-api/v1")
@RequiredArgsConstructor
public class CategoryController {

	private final ICategoryService categoryService;

	// http://localhost:8081/product-api/v1/categories
	@PostMapping( "/categories")
	ResponseEntity<Void> addCategory(@RequestBody CategoryDto categoryDto) {
		categoryService.addCategory(categoryDto);
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
	}

	// http://localhost:8081/product-api/v1/categories
	@PutMapping("/categories")
	ResponseEntity<Void>  updateCategory(@RequestBody CategoryDto categoryDto) {
		categoryService.updateCategory(categoryDto);
		return ResponseEntity.accepted().build();
	}

//	http://localhost:8081/product-api/v1//categories/categoryId/100
	@DeleteMapping("/categories/categoryId/{categoryId}")
	ResponseEntity<Void>  deleteCategory(@PathVariable int categoryId) {
	  categoryService.deleteCategory(categoryId);
	  return ResponseEntity.ok().build();
	}

//	http://localhost:8081/product-api/v1/categories
	@GetMapping("/categories")
	ResponseEntity<List<CategoryDto>> getAll() {
		return ResponseEntity.ok(categoryService.getAll());
	}

//	http://localhost:8081/product-api/v1/categories/categoryId/1
	@GetMapping("/categories/categoryId/{categoryId}")
	ResponseEntity<CategoryDto> getById(@PathVariable int categoryId) {
		return ResponseEntity.ok(categoryService.getById(categoryId));
		
	}
}







