package com.infy.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.product.dto.SubscribedProductDTO;
import com.infy.product.entity.CompositeKey;
import com.infy.product.entity.SubscribedProduct;
import com.infy.product.repository.SubscribedProductRepository;

@Service
public class SubscribedProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SubscribedProductRepository subsProductRep;
	
	public List<SubscribedProductDTO> getAllSubProducts() {

		List<SubscribedProduct> subProducts = subsProductRep.findAll();
		List<SubscribedProductDTO> subProductDTOs = new ArrayList<>();

		for (SubscribedProduct subProduct : subProducts) {
			SubscribedProductDTO subProductDTO = SubscribedProductDTO.valueOf(subProduct);
			subProductDTOs.add(subProductDTO);
		}

		logger.info("Subscribed Product details : {}", subProductDTOs);
		return subProductDTOs;
	}

	public SubscribedProductDTO getSpecificSubProduct(Integer buyerId, Integer productId) {
		CompositeKey compKey = new CompositeKey(buyerId, productId);
		Optional<SubscribedProduct> subProd = subsProductRep.findById(compKey);
		SubscribedProductDTO subProdDTO = SubscribedProductDTO.valueOf(subProd.get());
		return subProdDTO;
	}
	
	public void deleteSubProduct(Integer buyerId, Integer productId) throws Exception{
		CompositeKey compKey = new CompositeKey(buyerId, productId);
		subsProductRep.deleteById(compKey);
	}

	public void addSubProduct(SubscribedProductDTO subProdDTO) {
		SubscribedProduct subProd = new SubscribedProduct();
		subProd.setBuyerId(subProdDTO.getBuyerId());
		subProd.setProductId(subProdDTO.getProductId());
		subProd.setQuantity(subProdDTO.getQuantity());
		subsProductRep.save(subProd);
	}

}
