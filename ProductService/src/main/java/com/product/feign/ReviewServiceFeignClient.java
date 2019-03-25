package com.product.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.domain.Review;
import com.product.feign.callbacks.ReviewServiceCallback;

@FeignClient(name="${product.review.reviewservicename}", fallback=ReviewServiceCallback.class)
public interface ReviewServiceFeignClient {
	

	   @RequestMapping("/{productid}/reviews")	   
	   public List<Review> getProductReviews(@PathVariable(value="productid") Integer productid);

	   
	   public static  String somethingWentWrong(){
		   return "something went wrong";
	   }

}
