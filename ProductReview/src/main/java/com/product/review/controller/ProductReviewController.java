package com.product.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.review.domain.Review;
import com.product.review.service.ProdcutReviewService;

@RestController
@RequestMapping
public class ProductReviewController {

	@Autowired
	private ProdcutReviewService reviewService;

	@GetMapping(value = "/{productid}/reviews")
	public ResponseEntity<?> getProductReviews(@PathVariable("productid") Integer productID) {
		List<Review> reviews = reviewService.getProductReviews(productID);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping(value = "/{productid}/reviews/{reviewid}")
	public ResponseEntity<?> getProductReviews(@PathVariable("productid") Integer productID,
			@PathVariable("reviewid") Integer reviewID) {
		Review review = reviewService.getReviewById(productID, reviewID);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PostMapping(value = "/{productid}/reviews")
	public ResponseEntity<?> createProductReview(@PathVariable("productid") Integer productID,
			@RequestBody Review review) {
		review.setProductId(productID);
		System.out.println("save review  called...............");
		Review review1 = reviewService.saveProductReview(review);
		System.out.println("save review  called..............."+review1.getReviewId());
		return new ResponseEntity<>(review1, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{productid}/reviews/{reviewid}")
	public ResponseEntity<?> updateProductReview(@PathVariable("productid") Integer productID,
			@PathVariable("reviewid") Integer reviewID, @RequestBody Review review) {
		
		review.setProductId(productID);
		review.setReviewId(reviewID);
		Review reviewResponse = reviewService.saveProductReview(review);
		return new ResponseEntity<>(reviewResponse, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{productid}/reviews/{reviewid}")
	public ResponseEntity<?> deleteProductReview(@PathVariable("productid") Integer productID,
			@PathVariable("reviewid") Integer reviewID) {		
		reviewService.deleteProductReview(productID, reviewID);
		return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
	}

}
