package com.product.review.util;

import java.util.Optional;

public class ReviewUtil {

	public static <T> boolean isNotPresent(Optional<T> optional) {
		return !optional.isPresent();
	}
	
	public static <T> boolean isPresent(Optional<T> optional) {
		return optional.isPresent();
	}
}
