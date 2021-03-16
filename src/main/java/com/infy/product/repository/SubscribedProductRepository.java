package com.infy.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.product.entity.CompositeKey;
import com.infy.product.entity.SubscribedProduct;

public interface SubscribedProductRepository extends JpaRepository<SubscribedProduct, CompositeKey>{


}
