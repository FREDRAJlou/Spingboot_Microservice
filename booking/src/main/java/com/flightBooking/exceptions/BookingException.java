package com.flightBooking.exceptions;

public class BookingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookingException(Exception e) {
		super(e);
		System.out.print("Exception occured during booking ===> ");
		e.printStackTrace();
	}

}
