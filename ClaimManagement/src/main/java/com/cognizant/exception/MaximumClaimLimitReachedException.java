package com.cognizant.exception;

@SuppressWarnings("serial")
public class MaximumClaimLimitReachedException extends RuntimeException {

	public MaximumClaimLimitReachedException(String message) {
        super(message);

}
}
