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

	
}
