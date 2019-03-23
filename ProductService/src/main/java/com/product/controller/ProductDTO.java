package com.product.controller;

import java.util.ArrayList;
import java.util.List;

import com.product.domain.Review;

public class ProductDTO {

	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	private Integer productId;
	private List<Review> reviewList=new ArrayList<Review>();

	public List<Review> getReviewList(){
		return reviewList;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	

	
}
