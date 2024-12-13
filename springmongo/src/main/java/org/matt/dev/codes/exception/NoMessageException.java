package org.matt.dev.codes.exception;

public class NoMessageException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMessageException(String message) {
        super(message);
    }
}
