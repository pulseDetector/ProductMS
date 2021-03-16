package com.infy.product.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable{
	@Column(name="buyerid")
	Integer buyerId;
	@Column(name="prodid")
	Integer productId;
	
	public CompositeKey() {
		
	}
	
	public CompositeKey(Integer buyerId, Integer productId) {
		this.buyerId = buyerId;
		this.productId = productId;
	}
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
}
