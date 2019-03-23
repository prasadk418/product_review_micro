package com.product.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Product implements Serializable{ //extends ResourceSupport 
	
private static final long serialVersionUID = 123456L;
@Id	
@GeneratedValue
private Integer productId;
@Column
private String productName;
@Column
private Integer prodcutStock;
@Column
private String description;

public Product(){}

public Product(Integer productId, String productName, Integer prodcutStock, String description) {
	super();
	this.productId = productId;
	this.productName = productName;
	this.prodcutStock = prodcutStock;
	this.description = description;
}
public Integer getProductId() {
	return productId;
}
public void setProductId(Integer productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}

public Integer getProdcutStock() {
	return prodcutStock;
}
public void setProdcutStock(Integer prodcutStock) {
	this.prodcutStock = prodcutStock;
}
}
