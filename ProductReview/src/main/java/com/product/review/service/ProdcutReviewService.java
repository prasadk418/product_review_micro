package com.product.review.service;

import java.util.List;

import com.product.review.domain.Review;

public interface ProdcutReviewService {
	List<Review> getProductReviews(Integer productId);
	Review getReviewById(Integer reviewId);
	Review getReviewById(Integer productId, Integer reviewId);	
	Review saveProductReview(Review review);
	Review updateProductReview(Review review);
	void deleteProductReview(Integer productID, Integer reviewID);	
}
