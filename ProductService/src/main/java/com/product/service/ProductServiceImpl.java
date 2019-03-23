package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.domain.Product;
import com.product.exceptions.ProductNotFoundException;
import com.product.repository.ProductRepository;
import static com.product.util.ProductUtil.isNotPresent;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repo;
	
	public List<Product> getAllProducts(){
		return repo.findAll();
	}

	@Override
	public Product getProduct(Integer productId) {
		
		Optional<Product> product=repo.findById(productId);
		if(isNotPresent(product))
		{
			throw new ProductNotFoundException(productId + "- Requested product Details not Found..!");
		}
		return product.get();
	}
	
	
	@Override
	public Product saveProduct(Product product) {
		 return repo.save(product);
		
	}

	@Override
	public void deleteProduct(Integer productId) {
		repo.deleteById(productId);
				
	}

	
	@Override
	public Product updateProduct(Product product) {
 
		return repo.save(product);
	}
}
