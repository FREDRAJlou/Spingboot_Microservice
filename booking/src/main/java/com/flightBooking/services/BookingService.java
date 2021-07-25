package com.flightBooking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightBooking.entities.Booking;
import com.flightBooking.repositories.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepo;

	public Booking saveBooking(Booking booking) {
		return bookingRepo.save(booking);
	}

	public List<Booking> getBookingByPnr(String pnr) {
		List<Booking> bookings = this.bookingRepo.findByPnr(pnr);
		return bookings;
	}

	public List<Booking> getBookingByName(String name) {
		List<Booking> bookings = this.bookingRepo.findByBooker(name);
		return bookings;
	}

	public List<Booking> getBookings() {
		List<Booking> bookings = this.bookingRepo.findAll();
		return bookings;
	}

	public Booking deleteBooking(Long id) {
		this.bookingRepo.deleteById(id);
		return new Booking();
	}
}
