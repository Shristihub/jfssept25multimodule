package com.productinventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productinventory.model.Inventory;
import com.productinventory.repository.IInventoryRepository;

@Service
public class InventoryServiceImpl implements IInventoryService{
	
	@Autowired
	private IInventoryRepository repository;

	@Override
	public void addStock(int productId, int stock) {
		Inventory inventory = new Inventory(productId, null, stock);
		repository.save(inventory);
	}

	@Override
	public void updateStock(int productId, int stock, String type) {
		Inventory inventory = repository.findByProductId(productId)
								.orElseThrow(()-> new RuntimeException("invalid productId"));
		int existingstock = inventory.getStock(); // get the old stock
		if(type.equals("u"))
			inventory.setStock(stock+existingstock); //add to the new stock
		else
			inventory.setStock(existingstock-stock); //add to the new stock
		repository.save(inventory);
	}

	@Override
	public int checkStock(int productId) {
		Inventory inventory = repository.findByProductId(productId)
				.orElseThrow(()-> new RuntimeException("invalid productId"));
		return inventory.getStock();
	}
 
}
