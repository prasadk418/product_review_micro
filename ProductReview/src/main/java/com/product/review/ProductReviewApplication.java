package com.product.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaClient
public class ProductReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductReviewApplication.class, args);
	}
	
	@Bean	
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
