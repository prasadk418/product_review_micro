package com.product.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.domain.Review;

@FeignClient(name="${spring.application.name}")
public interface ProductServiceFeignClient {
	
	   @RequestMapping("/{productid}/reviews")
	   public List<Review> getProductReviews(@PathVariable(value="productid") Integer productid);


}
