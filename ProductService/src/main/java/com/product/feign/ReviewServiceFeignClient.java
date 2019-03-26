package com.product.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.product.domain.Review;
import com.product.feign.callbacks.ReviewServiceCallback;

@FeignClient(name="${product.review.reviewservicename}", fallback=ReviewServiceCallback.class)
public interface ReviewServiceFeignClient {
	

	   @GetMapping("/{productid}/reviews")	   
	   public List<Review> getProductReviews(@PathVariable(value="productid") Integer productid);
	   
	   @GetMapping(value = "/{productid}/reviews/{reviewid}")
	   public Review getProductReviewById(@PathVariable("productid") Integer productID, @PathVariable("reviewid") Integer reviewID);
		
	   @PostMapping("/{productid}/reviews")	   				 
	   public Review saveProductReview(@PathVariable(value="productid")Integer productId, @RequestBody Review review1);
	   
	   @PutMapping(value = "/{productid}/reviews/{reviewid}")
	   public Integer updateReview(@PathVariable("productid") Integer productId,
				@PathVariable("reviewid") Integer reviewId, @RequestBody Review review1);
	   
	   @DeleteMapping(value = "/{productid}/reviews/{reviewid}")
		public void deleteProductReview(@PathVariable("productid") Integer productID, @PathVariable("reviewid") Integer reviewID);
			  
}
