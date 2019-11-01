package com.avin.exception;

public class SecurityUserNotFoundException extends RuntimeException {

	public SecurityUserNotFoundException() {
		super("user not found");
	}
}
