package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
