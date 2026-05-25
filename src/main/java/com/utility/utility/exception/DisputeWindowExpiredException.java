package com.utility.utility.exception;

public class DisputeWindowExpiredException
        extends RuntimeException {

	private static final long serialVersionUID = 1L;
    public DisputeWindowExpiredException(String message) {
        super(message);
    }
}