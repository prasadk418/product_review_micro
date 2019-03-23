package com.product.domain;

import java.io.Serializable;

public class Review implements Serializable{
	
	private static final long serialVersionUID = 123456L;
	
	
	private Integer reviewId;
	
	private Integer productId;
		
	private String description;
	
	private Integer rating;
	
	public Review(){
		
	}
		
	public Review(Integer reviewId, Integer productId, String description, Integer rating) {
		super();
		this.reviewId = reviewId;
		this.productId = productId;
		this.description = description;
		this.rating = rating;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
}
