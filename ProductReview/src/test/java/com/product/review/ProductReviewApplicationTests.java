package com.product.review;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.product.review.domain.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductReviewApplicationTests {

	@LocalServerPort
	private int port;

	private String PROTOCOL = "http";
	private String SERVER_NAME = "localhost";

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	Review review;

	@Before
	public void init() {
		review = new Review(100, 1000, "Good Product, working fine", 4);
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void getProductDetails() {

		ResponseEntity<List> response = restTemplate.getForEntity(buildUrl("/1000/reviews"), List.class);
		Assert.isTrue(response.getStatusCode().equals(HttpStatus.OK));
		Assert.isTrue(response.getBody().size() > 0);

	}

	@Test
	public void createProductReview() {
		HttpEntity<Review> reviewEntity = new HttpEntity<Review>(review);
		ResponseEntity<Review> reviewResponse = restTemplate.exchange(buildUrl("/1000/reviews"), HttpMethod.POST,
				reviewEntity, Review.class);

		Assert.isTrue(reviewResponse.getStatusCode().equals(HttpStatus.CREATED));

	}

	@Test
	public void updateProductReview() {

		review.setProductId(1000);
		review.setDescription("Updated...!");
		review.setReviewId(999);
		HttpEntity<Review> reviewEntity = new HttpEntity<Review>(review);

		ResponseEntity<Review> reviewResponse = restTemplate.exchange(buildUrl("/1000/reviews/999"), HttpMethod.PUT,
				reviewEntity, Review.class);

		Assert.isTrue(reviewResponse.getStatusCode().equals(HttpStatus.OK));

	}

	@Test
	public void deleteProductReview() {

		ResponseEntity<List> response1 = restTemplate.getForEntity(buildUrl("/1000/reviews"), List.class);		
		Review r=(Review)response1.getBody().get(0);						
		restTemplate.delete(buildUrl("/"+r.getProductId()+"/reviews/"+r.getReviewId()));
		ResponseEntity<Review> response = restTemplate.getForEntity(buildUrl("/"+r.getProductId()+"/reviews/"+r.getReviewId()), Review.class);

		Assert.isTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));

	}

	public String buildUrl(String url) {
		return PROTOCOL + "://" + SERVER_NAME + ":" + port + url;
	}

}
