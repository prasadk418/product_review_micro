package com.product.review.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OperationNotPerformed extends RuntimeException {

	
	private static final long serialVersionUID = 123456L;

	public OperationNotPerformed() {
		
	}

	public OperationNotPerformed(String message) {
		super(message);
		
	}

}
