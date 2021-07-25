package com.flightBooking.controller;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightBooking.entities.Airline;
import com.flightBooking.entities.Booking;
import com.flightBooking.entities.Discount;
import com.flightBooking.entities.Flight;
import com.flightBooking.services.AirlineService;

@RestController
@RequestMapping("airlines")
public class AirlineController {

	@Autowired
	private AirlineService airlineService;

//	@Autowired
//	private RestTemplate restTemplate;
	
	private List<Booking> bookingList;
	
	@GetMapping("getTodayBookings")
	public List<Booking> getTodayBookings() {
		return this.bookingList;
	}
	
	@KafkaListener(topics = "kafka_books", groupId="group_id", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(Booking book) {
	    System.out.println("Consumed Booking: " + book);
	    if(!this.bookingList.contains(book))
	    this.bookingList.add(book);
	}

	@GetMapping("getAllAirlines")
//	@Cacheable(value = "airlines")
	public List<Airline> getAllAirlines() {
		return this.airlineService.getAirlines();
	}

	@GetMapping("getAirlineByName/{name}")
//	@Cacheable(value = "airlines")
	public List<Airline> getAirlineByName(@PathVariable("name") String name) {
		return this.airlineService.getAirlineByName(name);
	}

	@PostMapping("saveAirline")
	public Airline saveAirline(@RequestBody Airline airline) {
		System.out.println("Saving Airline " + airline);
		return this.airlineService.saveAirline(airline);
	}

	@DeleteMapping("deleteAirline/{id}")
	public Airline deleteAirline(@PathVariable("id") Long id) {
		System.out.println("Deleteing Airline " + id);
		this.airlineService.deleteAirline(id);
		return null;
	}

	@GetMapping("getFlights")
	public List<Flight> getFlights() {
		return this.airlineService.getFlights();
	}

	@GetMapping("getFlightByName/{query}")
//	@Cacheable(value = "flights")
	public List<Flight> getFlightByName(@PathVariable("query") String query) {
		String name = "", number = "", model = "";
		String[] params = query.split(",");
		System.out.print(params);
		name = (params.length > 0 && !params[0].equals("")) ? params[0] : "%";
		number = (params.length > 1 && !params[1].equals("")) ? params[1] : "%";
		model = (params.length > 2 && !params[2].equals("")) ? params[2] : "%";
		return this.airlineService.getFlightByName(name, number, model);
	}

	@GetMapping("getFlightByLoc/{query}")
//	@Cacheable(value = "flights")
	public List<Flight> getFlightByLoc(@PathVariable("query") String query) {
		String from = "", to = "";
		String[] params = query.split(",");
		from = (params.length > 0 && params[0].equals("")) ? "%": params[0] ;
		to = (params.length > 1 && params[1].equals("")) ?"%" :  params[1] ;
		System.out.println("query"+query+" ==>"+params+" from: "+from+" ,to: "+to);
		return this.airlineService.getFlightByLoc(from, to);
	}
	
	@GetMapping("getFlightLocs/{query}")
//	@Cacheable(value = "flights")
	public List<String> getFlightLocs(@PathVariable("query") String query) {
		String locType = "", loc = "";
		String[] params = query.split(",");
		locType = (params.length > 0 && params[0].equals("")) ? "%": params[0] ;
		loc = (params.length > 1 && params[1].equals("")) ?"%" :  params[1] ;
		System.out.println("query"+query+" ==>"+params+" locType: "+locType+" ,loc: "+loc);
		return this.airlineService.getFlightLocs(locType, loc).stream().distinct().collect(Collectors.toList());
	}

	@DeleteMapping("deleteFlight/{id}")
	@CacheEvict
	public Airline deleteFlight(@PathVariable("id") Long id) {
		System.out.println("Deleteing Flight " + id);
		this.airlineService.deleteFlight(id);
		return null;
	}

	@GetMapping("getDiscounts")
	public List<Discount> getDiscounts() {
		return this.airlineService.getDiscounts();
	}

	@GetMapping("getDiscountByCode/{code}")
	public List<Discount> getDiscountByCode(@PathVariable String code) {
		return this.airlineService.getDiscountByCode(code);
	}

	@PostMapping("saveDiscount")
	public Discount saveDiscount(@RequestBody Discount discount) {
		return this.airlineService.saveDiscount(discount);
	}
	
	@PutMapping("updateDiscount")
	public Discount updateDiscount(@RequestBody Discount discount) {
		return this.airlineService.saveDiscount(discount);
	}

	@PostMapping("saveFlight")
	@CachePut(value = "flights")
	public Flight saveFlight(@RequestBody Flight flight) {
		return this.airlineService.saveFlight(flight);
	}
	
	@PutMapping("updateFlight")
	@CachePut(value = "flights")
	public void updateFlight(@RequestBody Flight flight) {
		this.airlineService.saveFlight(flight);
	}

}
