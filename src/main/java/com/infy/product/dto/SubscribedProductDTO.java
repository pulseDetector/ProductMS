package com.infy.product.dto;

import com.infy.product.entity.SubscribedProduct;

public class SubscribedProductDTO {
	
	Integer buyerId;
	Integer productId;
	Integer quantity;
	
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public static SubscribedProductDTO valueOf(SubscribedProduct subProduct) {
		SubscribedProductDTO spDTO = new SubscribedProductDTO();
		spDTO.setBuyerId(subProduct.getBuyerId());
		spDTO.setProductId(subProduct.getProductId());	
		spDTO.setQuantity(subProduct.getQuantity());
		return spDTO;
	}
	

}
