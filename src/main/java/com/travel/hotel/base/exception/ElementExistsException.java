package com.travel.hotel.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A RuntimeException used to denote an error that the element already exists
 * and the operation cannot proceed further.
 */
public class ElementExistsException extends RuntimeException {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElementExistsException.class);

	private static final long serialVersionUID = 3663104230224233667L;

	/**
	 * Instantiates a new element exists exception.
	 *
	 * @param message the message
	 */
	public ElementExistsException(String message) {
		super(message);
		LOGGER.error(message);
	}

	/**
	 * Instantiates a new element exists exception.
	 *
	 * @param message the message
	 * @param t       the exception to wrap
	 */
	public ElementExistsException(String message, Throwable t) {
		super(message, t);
		LOGGER.error(message, t);
	}

}
