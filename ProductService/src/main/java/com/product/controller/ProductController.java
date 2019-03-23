package com.product.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.product.domain.Product;
import com.product.domain.Review;
import com.product.service.ProductReviewService;
import com.product.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productSrervice;

	@Autowired
	private ProductReviewService productReviewService;
	
	
	@GetMapping("/")
	public ResponseEntity<List<Review>> getProduct() {
		//List<ProductDTO> dtos=new ArrayList<ProductDTO>();
		List<Review> reviewList=productReviewService.getProductReviews(1);
		return new ResponseEntity<List<Review>>(reviewList, HttpStatus.OK);
	}

	@GetMapping("/{productid}")
	public ResponseEntity<Product> getProductById(@PathVariable("productid") Integer productId) {
		return new ResponseEntity<Product>(productSrervice.getProduct(productId), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product productDetails) {
		Product product = productSrervice.saveProduct(productDetails);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

	
	@PutMapping("/{productid}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productid") Integer productId,
			@Valid @RequestBody Product productDetails) throws Exception {
		Product product = productSrervice.getProduct(productId);
		product.setProductName(productDetails.getProductName());
		product.setProdcutStock(productDetails.getProdcutStock());

		final Product p = productSrervice.updateProduct(product);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@DeleteMapping("/{productid}")
	public ResponseEntity<?> deleteProduct(@PathVariable("productid") Integer productId) {
		Product product = productSrervice.getProduct(productId);
		productSrervice.deleteProduct(product.getProductId());
		return new ResponseEntity<>("Product Deleted successfully", HttpStatus.OK);
	}
	

	@PostMapping(value = "/{productid}/reviews")
	public ResponseEntity<?> addReview(@PathVariable("productid") Integer productId, @RequestBody Review review1) {

		review1.setProductId(productId);
		Review review = productReviewService.saveProductReview(productId, review1);
		return new ResponseEntity<>(review, HttpStatus.CREATED);

	}

	@PutMapping(value = "/{productid}/reviews/{reviewid}")
	public ResponseEntity<?> updateReview(@PathVariable("productid") Integer productId,
			@PathVariable("reviewid") Integer reviewId, @RequestBody Review review1) {

		Integer reviewID = productReviewService.updateProductReview(productId, reviewId, review1);

		return new ResponseEntity<>(reviewID, HttpStatus.CREATED);

	}

	@DeleteMapping(value = "/{productid}/reviews/{reviewid}")
	public ResponseEntity<?> deleteReview(@PathVariable("productid") Integer productId,
			@PathVariable("reviewid") Integer reviewId) {

		productReviewService.deleteProductReview(productId, reviewId);
		return new ResponseEntity<>("Deleted Successfully..!", HttpStatus.OK);
	}

}
