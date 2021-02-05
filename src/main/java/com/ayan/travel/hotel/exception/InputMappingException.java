package com.ayan.travel.hotel.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A RuntimeException used to denote an error that the madatory fields are
 * unavailable and the operation cannot proceed further.
 */
public class InputMappingException extends RuntimeException {

	private static final Logger LOGGER = LoggerFactory.getLogger(InputMappingException.class);

	private static final long serialVersionUID = 3663104230224233667L;

	/**
	 * Instantiates a new mapping exception.
	 *
	 * @param message the message
	 */
	public InputMappingException(String message) {
		super(message);
		LOGGER.error(message);
	}

	/**
	 * Instantiates a new mapping exception.
	 *
	 * @param message the message
	 * @param t       the exception to wrap
	 */
	public InputMappingException(String message, Throwable t) {
		super(message, t);
		LOGGER.error(message, t);
	}

}
