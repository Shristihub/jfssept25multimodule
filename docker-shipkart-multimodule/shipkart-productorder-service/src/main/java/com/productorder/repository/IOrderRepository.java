package com.productorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productorder.model.Orders;

public interface IOrderRepository extends JpaRepository<Orders, Integer>{

	
}
