package com.amadeus.RIQBuilder;

public class NoAvailableCESException extends Exception {

	String nameRIQ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoAvailableCESException(String input) {
		nameRIQ = input;
	}
	
	public String getCulperit() {
		return nameRIQ;
	}

}
