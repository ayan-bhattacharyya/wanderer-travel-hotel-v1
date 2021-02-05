package com.ayan.travel.hotel.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementUpdatedException extends RuntimeException {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElementUpdatedException.class);

	/**
	 * Instantiates a new element updated exception.
	 *
	 * @param message the message
	 */
	public ElementUpdatedException(String message) {
		super(message);
		LOGGER.error(message);
	}

	/**
	 * Instantiates a new element updated exception.
	 *
	 * @param message the message
	 * @param t       the exception to wrap
	 */
	public ElementUpdatedException(String message, Throwable t) {
		super(message, t);
		LOGGER.error(message, t);
	}

}
