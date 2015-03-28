package com.ddf.commons.exceptions.services;

/**
 * Exception which is thrown every time a validation of input data fails on a
 * service
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
public class ValidationException extends RuntimeException {

	/* static fields */
	private static final long serialVersionUID = 1L;

	/* instance variables */

	/* constructors */
	/**
	 * Constructor
	 */
	public ValidationException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ValidationException(String message, Throwable cause,
		boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public ValidationException(Throwable cause) {
		super(cause);
	}

	/* Methods */

	/* Getters & Setters */
}
