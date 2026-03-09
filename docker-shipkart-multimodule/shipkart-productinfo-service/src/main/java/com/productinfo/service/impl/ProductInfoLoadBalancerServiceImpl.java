package com.productinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import com.productinfo.exception.ProductNotFoundException;
import com.productinfo.feign.IProductCatalogFeignClient;
import com.productinfo.model.Product;
import com.productinfo.service.IProductInfoService;

//@Service
public class ProductInfoLoadBalancerServiceImpl implements IProductInfoService {

	@Autowired
	private IProductCatalogFeignClient feignClient;
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	// select the interface not the class
	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public List<Product> getAll() {
		// pass the other service application name
		ServiceInstance instance = loadBalancerClient.choose("product-catalog");
		System.out.println(".........details..........");
		System.out.println("port " + instance.getPort());
		System.out.println("scheme " + instance.getScheme());
		System.out.println("serviceId" + instance.getServiceId());
		System.out.println("instanceId " + instance.getInstanceId());
		System.out.println("host " + instance.getHost());
		System.out.println("metadata " + instance.getMetadata());
		return feignClient.viewAll();
	}

	@Override
	public Product getById(int productId) throws ProductNotFoundException {
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances("product-catalog");
    	serviceInstances.forEach(instance->{
    		System.out.println(".........Details of instances..........");
    		System.out.println("port "+instance.getPort());
    		System.out.println("scheme "+instance.getScheme());
    		System.out.println("serviceId "+instance.getServiceId());
    		System.out.println("instanceId "+instance.getInstanceId());
    		System.out.println("host "+instance.getHost());
    		System.out.println("metadata "+instance.getMetadata());
    		
    	});
		return feignClient.viewById(productId);
	}

	@Override
	public List<Product> getByCategory(String category) throws ProductNotFoundException {
		return feignClient.viewByCategory(category);
	}

	@Override
	public List<Product> getByBrandAndPayType(String brand, String payment) throws ProductNotFoundException {
		return null;
//		return feignClient.viewByBrandAndPayType(brand, payment);
	}

	@Override
	public List<Product> getByColor(String color) throws ProductNotFoundException {
//		return feignClient.viewByColor(color);
		return null;
	}

	@Override
	public List<Product> getByNameContains(String name) throws ProductNotFoundException {
//		return feignClient.viewByNameContains(name);
		return null;
	}

}
