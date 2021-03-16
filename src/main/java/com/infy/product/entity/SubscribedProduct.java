package com.infy.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Table(name="subscribedproduct")
@Entity
@IdClass(CompositeKey.class)
public class SubscribedProduct {
	
	@Id
	@Column(name="buyerid")
	Integer buyerId;
	@Id
	@Column(name="prodid")
	Integer productId;
//	@Column(nullable = false)
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
	public SubscribedProduct() {
		super();
	}
}
