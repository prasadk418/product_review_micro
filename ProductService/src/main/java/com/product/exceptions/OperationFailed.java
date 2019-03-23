package com.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OperationFailed extends RuntimeException {

	private static final long serialVersionUID = 123456L;
	
	public OperationFailed(String msg){
		super(msg);
	}
	
}
