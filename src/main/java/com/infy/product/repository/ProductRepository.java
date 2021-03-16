package com.infy.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByCategory(String category);

	List<Product> findByProductName(String productName);

}
