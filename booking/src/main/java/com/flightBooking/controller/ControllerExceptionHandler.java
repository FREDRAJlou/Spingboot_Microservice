package com.flightBooking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flightBooking.exceptions.BookingException;

@ControllerAdvice
public class ControllerExceptionHandler{
	
	@ExceptionHandler(BookingException.class)
	public ResponseEntity<String> handleException(Exception e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body("error");
	}

	
}
