package com.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.productcatalog.model.entities.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "select p from Product p join p.categories c where c.categoryName=?1")
	List<Product> findByCategory(String category);

	@Query(value = """
			select p from Product p join p.brand b join p.paymentModes pm where b.brandName=?1
			and pm=?2
			""")
	List<Product> findByBrandAndPayType(String brand, String payment);

	@Query(value = "select p from Product p join p.features f where f.color=?1 ")
	List<Product> findByColor(String color);

	@Query(value = """
			select p from Product p join p.categories c  join p.deliveryTypes dt where c.categoryName =?1and dt=?2
			""")
	List<Product> findByCategoryAndDelivery(String category, String delivery);

	@Query(value = "select p from Product p where p.productName like ?1  ")
	List<Product> findByNameContains(String name);

	@Query(value = """
			select p from Product p join p.offers o where p.productName=?1
			  and o.offerName=?2
			""")
	List<Product> findByNameOffers(String name, String offers);

}
