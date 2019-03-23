package com.product;

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

import com.product.domain.Product;
import com.product.domain.Review;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceApplicationTests {

	@LocalServerPort
	private int port;
	
	private String PROTOCOL="http";
	private String SERVER_NAME="localhost";
	
	
	TestRestTemplate restTemplate=new TestRestTemplate();
	HttpHeaders headers=new HttpHeaders();
	
	Product product;
	Review review;
	
	@Before
	public void init(){
	product=new Product(10, "Desktop", 20, "HP Desktops");	
	review=new Review(100,1000,"Good Product, working fine", 4);
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void getProductDetails(){
		
		ResponseEntity<List> response=restTemplate.getForEntity(buildUrl("/products/"), List.class);
		Assert.isTrue(response.getStatusCode().equals(HttpStatus.OK));
		Assert.isTrue(response.getBody().size() > 0);
		
	}

	@Test
	public void saveProductDetails(){
		HttpEntity<Product> productEntity=new HttpEntity<Product>(product);
		ResponseEntity<Product> responseProduct=restTemplate.exchange(buildUrl("/products/"), HttpMethod.POST , productEntity, Product.class);
	
		Assert.isTrue(responseProduct.getStatusCode().equals(HttpStatus.CREATED));
		Assert.isTrue(responseProduct.getBody().getProductId() != null);
		
	}
	
	@Test
	public void updateProductDetails(){		
		product.setProdcutStock(20);
		product.setProductId(1000);		
		HttpEntity<Product> productEntity=new HttpEntity<Product>(product);
		ResponseEntity<Product> responseProduct=restTemplate.exchange(buildUrl("/products/1000"), HttpMethod.PUT , productEntity, Product.class);
			
		Assert.isTrue(responseProduct.getStatusCode().equals(HttpStatus.OK));
		Assert.isTrue(responseProduct.getBody().getProductId() != null);
		
	}
	
	@Test
	public void updateProductDetails_NoProductDetailsFound(){					
		HttpEntity<Product> productEntity=new HttpEntity<Product>(product);
		ResponseEntity<Product> responseProduct=restTemplate.exchange(buildUrl("/products/10001"), HttpMethod.PUT , productEntity, Product.class);
				
		Assert.isTrue(responseProduct.getStatusCode().equals(HttpStatus.NOT_FOUND));		
		
	}
	
	
	@Test
	public void deleteProductDetails(){		
				
		restTemplate.delete(buildUrl("/products/1000"));
		ResponseEntity<Product> response=restTemplate.getForEntity(buildUrl("/products/1000"), Product.class);
							
		Assert.isTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
				
	}
	
	@Test
	public void createProductReview(){					
		HttpEntity<Review> reviewEntity=new HttpEntity<Review>(review);
		ResponseEntity<Review> reviewResponse=restTemplate.exchange(buildUrl("products/2/reviews"), HttpMethod.POST , reviewEntity, Review.class);
				
		Assert.isTrue(reviewResponse.getStatusCode().equals(HttpStatus.CREATED));		
		
	}
	
	@Test
	public void updateProductReview(){					
		HttpEntity<Review> reviewEntity=new HttpEntity<Review>(review);
		ResponseEntity<Review> reviewResponse=restTemplate.exchange(buildUrl("products/2/reviews"), HttpMethod.POST , reviewEntity, Review.class);
				
		Assert.isTrue(reviewResponse.getStatusCode().equals(HttpStatus.CREATED));		
		
	}
	
	@Test
	public void deleteProductReview(){					
		HttpEntity<Review> reviewEntity=new HttpEntity<Review>(review);
		ResponseEntity<Review> reviewResponse=restTemplate.exchange(buildUrl("products/2/reviews"), HttpMethod.POST , reviewEntity, Review.class);
				
		Assert.isTrue(reviewResponse.getStatusCode().equals(HttpStatus.CREATED));		
		
	}
	public String buildUrl(String url){
		return PROTOCOL+"://"+SERVER_NAME+":"+port+url; 
	}

}
