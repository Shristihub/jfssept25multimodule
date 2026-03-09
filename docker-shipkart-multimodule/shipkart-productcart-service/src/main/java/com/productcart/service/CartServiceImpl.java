package com.productcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.productcart.feign.IProductInfoClient;
import com.productcart.model.Cart;
import com.productcart.model.CartDto;
import com.productcart.model.CartItem;
import com.productcart.model.Product;
import com.productcart.repository.ICartRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {


	private final ICartRepository repository;
	private final IProductInfoClient infoClient;
	private final ModelMapper mapper;


	@Override
	@CircuitBreaker(name = "cartService",fallbackMethod = "fallbackAddToCart")
	public CartDto addToCart(int userId, int productId, int quantity) {
		// check if the user with this id is available in the cart table
		// if yes get the cart from the table
		Cart cart = repository.findByUserId(userId)
				// if cart not vailable means create a new cart
				.orElseGet(() -> {
					Cart newCart = new Cart();
					newCart.setCartItems(new ArrayList<>());
					return newCart;
				});
		// set the userId to the cart
		cart.setUserId(userId);

		// get the product from productinfo using productId
		Product product = infoClient.viewById(productId);
		// check if the list is empty in the cart
		if (cart.getCartItems().isEmpty()) {
			// create a new cartItem
			CartItem newcartItem = new CartItem();
			newcartItem.setProductId(productId);
			newcartItem.setProductName(product.getProductName());
			newcartItem.setPrice(product.getPrice());
			newcartItem.setQuantity(quantity);
			cart.getCartItems().add(newcartItem);
		} else {
			// if the user has the cart already with cartItems
			List<CartItem> existingItems = cart.getCartItems();
			// check if product is already available in the cart
			// from the cartItem get the productId and check with the incoming productId
			Optional<CartItem> existingItemOpt = existingItems.stream()
					.filter(cartItem -> cartItem.getProductId() == productId).findFirst();
			// if the item is already present in the cart
			if (existingItemOpt.isPresent()) {
				// get the item.
				CartItem existingCartItem = existingItemOpt.get();
//				 get the quantity and increment
				// add the new quantoty to the ole onw
				existingCartItem.setQuantity(quantity + existingCartItem.getQuantity());
				System.out.println(existingCartItem);
				// attach to the cart
				cart.setCartItems(existingItems);
			} else {
				// item not available in the cart.so add a new cartitem
				CartItem newcartItem = new CartItem();
				newcartItem.setProductId(productId);
				newcartItem.setProductName(product.getProductName());
				newcartItem.setPrice(product.getPrice());
				newcartItem.setQuantity(quantity);
				existingItems.add(newcartItem);
				System.out.println(existingItems);
				cart.setCartItems(existingItems);
			}
		}
		System.out.println(cart.getCartItems());
		// calculate the total price
		double total = cart.getCartItems().stream().mapToDouble(cartItem -> cartItem.getPrice() * quantity).sum();
		cart.setTotalPrice(total);
		// save it to the database;
		System.out.println();
		System.out.println(cart);
		Cart savedCart = repository.save(cart);
		return mapper.map(savedCart, CartDto.class);
	}
	
//	  CartItem createCartItem()
	
	public CartDto fallbackAddToCart(Exception e) {
		System.out.println("exception occured "+e);
		return new CartDto();
	}
	

	@Override
	public CartDto viewCart(int userId) {
		Cart cart =  repository.findByUserId(userId).orElseGet(() -> new Cart());
		return mapper.map(cart, CartDto.class);
	}

}
