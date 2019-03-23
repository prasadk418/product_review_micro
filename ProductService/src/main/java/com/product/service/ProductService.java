package com.product.service;

import java.util.List;
import java.util.Optional;

import com.product.domain.Product;

public interface ProductService {
	List<Product> getAllProducts();
	Product getProduct(Integer productId);
	Product saveProduct(Product product);
	Product updateProduct(Product product);
	void deleteProduct(Integer productId);		
}
