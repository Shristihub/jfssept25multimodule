package com.productcatalog.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalog.model.dtos.ProductDto;
import com.productcatalog.service.IProductService;
import com.productcatalog.web.ICourses;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/catalog-service/v1")
@RequiredArgsConstructor
@RefreshScope
@Profile("dev")
public class ProductController {

	private final IProductService productService;
	
	@Value("${message}")
	private String message;
	
	private final  ICourses courses;
	
	// http://localhost:8081/catalog-service/v1/show
	@GetMapping("/show")
	public String showMessage() {
		return message;
	}
	
	// http://localhost:8081/catalog-service/v1/show
		@GetMapping("/show-courses")
		public List<String> showCourses() {
			return courses.getCourses();
		}

	// http://localhost:8081/catalog-service/v1/admin/products
	@PostMapping( "/admin/products")
	ResponseEntity<Void> addProduct(@RequestBody ProductDto productDto) {
		productService.addProduct(productDto);
//		//wrap it in ResponseEntity<Void>
////		this returns the status as created and no body
		return ResponseEntity.status(HttpStatus.CREATED.value()).build();
	}

	// http://localhost:8081/catalog-service/v1/products
	@PutMapping("/admin/products")
	ResponseEntity<Void>  updateProduct(@RequestBody ProductDto productDto) {
		productService.updateProduct(productDto);
		return ResponseEntity.accepted().build();
	}

//	http://localhost:8081/catalog-service/v1/products/productId/1
	@DeleteMapping("/admin/products/productId/{productId}")
	ResponseEntity<Void>  deleteProduct(@PathVariable int productId) {
		HttpHeaders headers =  new HttpHeaders();
		headers.add("info", "delete one product");
		headers.add("timestamp", LocalTime.now().toString());
		productService.deleteProduct(productId);
		return ResponseEntity.ok().build();
	}

//	http://localhost:8081/catalog-service/v1/products
	@GetMapping("/products")
	ResponseEntity<List<ProductDto>> getAll() {
		List<ProductDto> products = productService.getAll();
		// create headers
		HttpHeaders headers =  new HttpHeaders();
		headers.add("info", "returns a list of products");
		headers.add("timestamp", LocalTime.now().toString());
		//add status
		// create a ReponseEntity Object and return it
		return ResponseEntity.status(HttpStatusCode.valueOf(200))
		              .headers(headers).body(products);
	}

//	http://localhost:8081/catalog-service/v1/products/productId/1
	@GetMapping("/products/productId/{productId}")
	ResponseEntity<ProductDto> getById(@PathVariable int productId) {
		HttpHeaders headers =  new HttpHeaders();
		headers.add("info", "returns one product by id");
		ProductDto productDto =  productService.getById(productId);
		return new ResponseEntity<>(productDto,headers, 200);
	}
//	http://localhost:8081/catalog-service/v1/products/category?categoryname=sports
	@GetMapping("/products/category")
	ResponseEntity<List<ProductDto>> getByCategory(@RequestParam() String categoryname) {
		List<ProductDto> products = productService.getByCategory(categoryname);
		return ResponseEntity.ok(products);
	}
//	http://localhost:8081/catalog-service/v1/products/brand/Samsung/payment/UPI
	@GetMapping("/products/brand/{brand}/payment/{payment}")
	ResponseEntity<List<ProductDto>> getByBrandAndPayType(@PathVariable String brand,@PathVariable String payment) {
		List<ProductDto> products = productService.getByBrandAndPayType(brand, payment);
		return ResponseEntity.ok(products);
	}
	
//	http://localhost:8081/catalog-service/v1/products/color/red
	@GetMapping("/products/color/{color}")
	ResponseEntity<List<ProductDto>> getByColor(@PathVariable String color) {
		List<ProductDto> products = productService.getByColor(color);
		return ResponseEntity.ok(products);
	}
	
//	http://localhost:8081/catalog-service/v1/products/category/Sports/delivery/prime
	@GetMapping("/products/category/{category}/delivery/{delivery}")
	ResponseEntity<List<ProductDto>> getByCategoryAndDelivery(@PathVariable String category,
			                                                 @PathVariable String delivery){
		List<ProductDto> products = productService.getByCategoryAndDelivery(category, delivery);
		return ResponseEntity.ok(products);
	}
	
//	http://localhost:8081/catalog-service/v1/products/name/bottle
	@GetMapping("/products/name/{name}")
	ResponseEntity<List<ProductDto>> getByNameContains(@PathVariable String name){
		List<ProductDto> products = productService.getByNameContains(name);
		return ResponseEntity.ok(products);
	}
	
//	http://localhost:8081/catalog-service/v1/products/name/bag/offers/CASH BACK
	@GetMapping("/products/name/{name}/offers/{offers}")
	ResponseEntity<List<ProductDto>> getByNameOffers(@PathVariable String name,@PathVariable String offers){
		List<ProductDto> products = productService.getByNameOffers(name, offers);
		return ResponseEntity.ok(products);
	}
}







