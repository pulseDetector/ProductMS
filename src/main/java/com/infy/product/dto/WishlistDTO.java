package com.infy.product.dto;


public class WishlistDTO {
Integer buyerId;
	
	Integer proId;
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	
	@Override
	public String toString() {
		return "WishlistDTO [buyerid=" + buyerId + ", prodid=" + proId + "]";
	}
}
