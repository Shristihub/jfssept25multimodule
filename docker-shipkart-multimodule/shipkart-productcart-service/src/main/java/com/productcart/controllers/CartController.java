package com.productcart.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productcart.model.CartDto;
import com.productcart.service.ICartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart-service/v1")
@RequiredArgsConstructor
public class CartController {

	private final ICartService cartService;
	
	//addToCart
	// http://localhost:8085/cart-service/v1/cart/add?userId=1&productId=1&quantity=2
	@PostMapping("/cart/add")
	ResponseEntity<CartDto> addToCart(@RequestParam int userId,@RequestParam int productId,@RequestParam int quantity){
		CartDto cartDto = cartService.addToCart(userId, productId, quantity);
		return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);
	}
	//viewCart
	@GetMapping("/cart/view/userId/{userId}")
	ResponseEntity<CartDto> viewCart(@PathVariable int userId){
		CartDto cart = cartService.viewCart(userId);
		return ResponseEntity.ok(cart);
	}
	
	//updateCart
    //removeFromCart
	//clearCart
	


}





