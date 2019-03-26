package com.product.feign.callbacks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.product.domain.Review;
import com.product.feign.ReviewServiceFeignClient;

@Component
public class ReviewServiceCallback implements ReviewServiceFeignClient{

	
	@Override
	public List<Review> getProductReviews(Integer productid) {
		// TODO Auto-generated method stub
		return new ArrayList<Review>();
	}

	@Override
	public Review saveProductReview(Integer productId, Review review1) {
		// TODO Auto-generated method stub
		
		System.out.println("Save Fall back method..............called...........");
		
		return null;
	}

	@Override
	public Integer updateReview(Integer productId, Integer reviewId, Review review1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductReview(Integer productID, Integer reviewID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Review getProductReviewById(Integer productID, Integer reviewID) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
