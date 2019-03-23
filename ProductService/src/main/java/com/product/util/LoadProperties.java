package com.product.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="product.review")
@Component
public class LoadProperties {

	public LoadProperties() {
		// TODO Auto-generated constructor stub
	}
	
	private String protocol;
	private String reviewservicename;
	
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getReviewservicename() {
		return reviewservicename;
	}
	public void setReviewservicename(String reviewservicename) {
		this.reviewservicename = reviewservicename;
	}
			
}
