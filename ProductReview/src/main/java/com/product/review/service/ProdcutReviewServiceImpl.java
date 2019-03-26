package com.product.review.service;

import static com.product.review.util.ReviewUtil.isNotPresent;
import static com.product.review.util.ReviewUtil.isPresent;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.review.domain.Review;
import com.product.review.exception.OperationNotPerformed;
import com.product.review.exception.ReviewNotFoundException;
import com.product.review.repository.ProductReviewRepository;

@Service
public class ProdcutReviewServiceImpl implements ProdcutReviewService {

	@Autowired
	private ProductReviewRepository reviewRepository; 
	
	@Override
	public List<Review> getProductReviews(Integer productId) {				
		return reviewRepository.findReviewByProductId(productId);
	}
	
	@Override
	public Review saveProductReview(Review review) {
		
		Review review1=reviewRepository.save(review);
		if(review1 == null){
			throw new OperationNotPerformed("Data not inserted into  DB.");
		}
		return review1;
	}

	@Override
	public Review updateProductReview(Review review) {
		getReviewById(review.getReviewId());
		return reviewRepository.save(review);				
	}
	
	@Transactional
	@Modifying
	public void deleteProductReview(Integer reviewID, Integer productID) {
		getReviewById(reviewID);
		reviewRepository.deleteById(reviewID);		
		Optional<Review> review=reviewRepository.findById(reviewID);
		if(isPresent(review)){
			throw new OperationNotPerformed("Data not deleted into  DB.");
		}		
		
	}

	@Override
	public Review getReviewById(Integer reviewId) {		
		Optional<Review> review=reviewRepository.findById(reviewId);
		if(isNotPresent(review)){
			throw new ReviewNotFoundException(reviewId+" : Review details not found...!");
		}
		return review.get();
	}

	@Override
	public Review getReviewById(Integer productId, Integer reviewId) {
		Optional<Review> review=reviewRepository.findReviewByProductId(productId, reviewId);
		if(isNotPresent(review)){
			throw new ReviewNotFoundException(reviewId+" : Review details not found...!");
		}
		return review.get();
	}

	
}
