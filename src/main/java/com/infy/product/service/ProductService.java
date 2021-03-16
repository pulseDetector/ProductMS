package com.infy.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infy.product.dto.ProductDTO;
import com.infy.product.entity.Product;
import com.infy.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ProductRepository productRep;
	
	public List<ProductDTO> getAllProducts() {

		List<Product> products = productRep.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Product product : products) {
			ProductDTO productDTO = ProductDTO.valueOf(product);
			productDTOs.add(productDTO);
		}

		logger.info("Product details : {}", productDTOs);
		return productDTOs;
	}
	
	public ProductDTO getSpecificProduct(Integer productId) throws Exception{
		
		ProductDTO productDTO = null;
		try {
		Optional<Product> optProduct = productRep.findById(productId);
		System.out.println(optProduct);
		if(optProduct.isPresent()) {
			Product product = optProduct.get();
			productDTO = ProductDTO.valueOf(product);
		} else {
			throw new Exception("Product ID Invalid");
		}
		} catch(Exception e) {
			throw e;
		}
		return productDTO;
	}
	
public List<ProductDTO> getProductsByCategory(String category) {
		
		List<ProductDTO> productDTOs = new ArrayList<>();
		List<Product> products = productRep.findByCategory(category);
		if(!products.isEmpty()) {
			for(Product product:products) {
				productDTOs.add(ProductDTO.valueOf(product));
			}
		}
		return productDTOs;
	}

public List<ProductDTO> getProductsByName(String productName) {
	
	List<ProductDTO> productDTOs = new ArrayList<>();
	List<Product> products = productRep.findByProductName(productName);
	if(!products.isEmpty()) {
		for(Product product:products) {
			productDTOs.add(ProductDTO.valueOf(product));
		}
	}
	return productDTOs;
}

public void updateProduct(Integer productId, ProductDTO product) {
	Product existingProduct = productRep.findById(productId).orElse(null);
	if (existingProduct !=null) {
		existingProduct.setBrand(product.getBrand());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setImage(product.getImage());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setProductId(product.getProductId());
		existingProduct.setProductName(product.getProductName());
		existingProduct.setRating(product.getRating());
		existingProduct.setStock(product.getStock());
		existingProduct.setSubcategory(product.getSubcategory());
		existingProduct.setSellerId(product.getSellerId());
		productRep.save(existingProduct);
	}
}
}
