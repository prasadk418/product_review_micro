package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.product.domain.Review;
import com.product.exceptions.OperationFailed;
import com.product.exceptions.ReviewNotFoundException;
import com.product.feign.ReviewServiceFeignClient;

@Service
public class ProductReviewService {
	
	@Value("${product.review.reviewservicename}")
	private String serviceName;
	
	@Autowired
	private ReviewServiceFeignClient reviewClient;

		
	public ProductReviewService() {
	}

	/**
	 * @param productId
	 * @param entity
	 * @return
	 * @throws RestClientException
	 */
	public Review saveProductReview(Integer productId,Review review1) throws RestClientException {
		System.out.println(productId+"---------service---------"+review1);
		Review review=reviewClient.saveProductReview(productId, review1);
		System.out.println("Review created : --------"+review.getReviewId());
		return review;
	}

	/**
	 * @param productId
	 * @param reviewId
	 * @param review1
	 * @return
	 * @throws RestClientException
	 * @throws ReviewNotFoundException
	 */
	public Integer updateProductReview(Integer productId, Integer reviewId, Review review1) throws RestClientException {

		
		Review review = getProductReviewById(productId, reviewId);
		
		review1.setReviewId(review.getReviewId());
		
		Integer updatedReviewId=reviewClient.updateReview(productId, reviewId, review1);
		if (updatedReviewId == null)
			throw new OperationFailed("Data not stored in database");

		return updatedReviewId;
	}
	
	/**
	 * @param productId
	 * @param reviewId
	 * @return
	 * @throws RestClientException
	 * @throws ReviewNotFoundException
	 */
	public void deleteProductReview(Integer productId, Integer reviewId)
			throws RestClientException, ReviewNotFoundException {
		getProductReviewById(productId, reviewId);		
		reviewClient.deleteProductReview(productId, reviewId);
		
	}


	/**
	 * @param productId
	 * @param reviewId
	 * @return
	 * @throws RestClientException
	 */
	public Review getProductReviewById(Integer productId, Integer reviewId)
			throws RestClientException, ReviewNotFoundException {
		Review review = reviewClient.getProductReviewById(productId, reviewId);				
		if (review == null) {
			throw new ReviewNotFoundException(reviewId + "- Requested review Details not Found..!");
		}
		return review;
	}
	
	/**
	 * @param productId
	 * @param reviewId
	 * @return
	 * @throws RestClientException
	 */
	public List<Review> getProductReviews(Integer productId)
			throws RestClientException, ReviewNotFoundException {
		List<Review> reviews =reviewClient.getProductReviews(productId); 
		return reviews;
	}
}
