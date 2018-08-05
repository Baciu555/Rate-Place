package com.baciu.exception;

public class PlaceExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlaceExistsException() {
		super();
	}
	
	public PlaceExistsException(String message) {
		super(message);
	}
}
