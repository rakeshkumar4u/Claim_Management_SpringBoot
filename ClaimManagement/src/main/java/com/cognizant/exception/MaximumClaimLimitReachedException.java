package com.cognizant.exception;

public class MaximumClaimLimitReachedException extends RuntimeException {
	public MaximumClaimLimitReachedException(String message) {
		super(message);
	}

}
