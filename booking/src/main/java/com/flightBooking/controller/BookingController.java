package com.flightBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flightBooking.entities.Booking;
import com.flightBooking.exceptions.BookingException;
import com.flightBooking.services.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private RestTemplate restTemplate;

//	@Autowired
//	KafkaTemplate<String, Booking> bookingProducer;
//	
	private static final String TOPIC = "kafka_bookings";

	@GetMapping("getAllBookings")
//	@Cacheable(value = "bookings")
	public List<Booking> getAllBookings() throws BookingException {
		try {
			return this.bookingService.getBookings();
		} catch (Exception e) {
			throw new BookingException(e);
		}
	}

	@GetMapping("testException")
	public String exc() throws BookingException {
		try {
			int i = 1 / 0;
			return "1" + i;
		} catch (Exception e) {
			throw new BookingException(e);
		}
	}

	@GetMapping("getFlights")
	public List<Object> getFlights() {
		System.out.print("Getting Flights using Rest Template");
		ResponseEntity<List<Object>> res = this.restTemplate.exchange("http://AIRLINESERVICE/airlines/getFlights",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>() {
				});
		return res.getBody();
	}

//	@GetMapping("getDiscount")
//	public Discount getDiscount() {
//		ResponseEntity<List<Discount>> res = this.restTemplate.exchange("", HttpMethod.GET, null,
//				new ParameterizedTypeReference<List<Discount>>() {
//				});
//		List<Discount> discount = res.getBody();
//		return discount.isEmpty() ? new Discount() : discount.get(0);
//	}

	@GetMapping("GetBookingByPnr/{pnr}")
//	@Cacheable(value = "bookings")
	public List<Booking> getBookingByPnr(@PathVariable String pnr) throws BookingException {
		try {
			return this.bookingService.getBookingByPnr(pnr);
		} catch (Exception e) {
			throw new BookingException(e);
		}
	}

	@GetMapping("getBookingByName/{name}")
//	@Cacheable(value = "bookings")
	public List<Booking> getBookingByName(@PathVariable String name) throws BookingException {
		try {
			return this.bookingService.getBookingByName(name);
		} catch (Exception e) {
			throw new BookingException(e);
		}
	}

	@PostMapping("saveBooking")
//	@Cacheable(value = "bookings")
	public Booking saveBooking(@RequestBody Booking booking) throws BookingException {
		try {
			Booking savedBooking = this.bookingService.saveBooking(booking);
//			bookingProducer.send(TOPIC, savedBooking);
			System.out.print(savedBooking);
			return savedBooking;
		} catch (Exception e) {
			throw new BookingException(e);
		}
	}

	@DeleteMapping("deleteBooking/{id}")
//	@Cacheable(value = "bookings")
	public Booking deletBooking(@PathVariable Long id) throws BookingException {
		try {
			return this.bookingService.deleteBooking(id);
		} catch (Exception e) {
			throw new BookingException(e);
		}
	}

}
