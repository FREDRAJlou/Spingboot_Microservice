package com.flightBooking.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.flightBooking.entities.Booking;
import com.flightBooking.exceptions.BookingException;
import com.flightBooking.repositories.BookingRepository;

@SpringBootTest
class BookingTests {

	@Autowired
	private BookingController bookingController;

	@MockBean
	private BookingRepository repository;

	@Test
	@Disabled
	void testGetAllBookings() throws BookingException {
		Assertions.assertNotEquals(this.bookingController.getAllBookings(), Collections.emptyList());
	}

	@Test
	public void testAllBooks() throws BookingException {

		Mockito.when(repository.findAll())
				.thenReturn(Arrays.asList(new Booking(), new Booking(), new Booking()));

		List<Booking> books = bookingController.getAllBookings();

		Assertions.assertSame(3, books.size());

	}

	@Test
	public void testBookingDelete() throws BookingException {
		Long id = 12l;
		this.bookingController.deletBooking(id);
		Mockito.verify(repository, Mockito.times(1)).deleteById(id);
	}

	@Test
	public void testBookingSave() throws BookingException {
		Booking booking = new Booking();
		Mockito.when(repository.save(booking)).thenReturn(booking);
		Booking booking1 = this.bookingController.saveBooking(booking);
		Assertions.assertEquals(booking, booking1);
	}

}
