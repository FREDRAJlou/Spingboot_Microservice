package com.flightBooking.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightBooking.entities.Airline;
import com.flightBooking.entities.Discount;
import com.flightBooking.entities.Flight;
import com.flightBooking.repositories.AirlineRepository;
import com.flightBooking.repositories.DiscountRepository;
import com.flightBooking.repositories.FlightRepository;

@Service
public class AirlineService {

	@Autowired
	private AirlineRepository airlineRepo;
	@Autowired
	private FlightRepository flightRepo;
	@Autowired
	private DiscountRepository discountRepo;

	public Airline saveAirline(Airline airline) {
		return airlineRepo.save(airline);
	}

	public void deleteAirline(Long id) {
		this.airlineRepo.deleteById(id);
	}

	public List<Airline> getAirlines() {
		List<Airline> airlines = this.airlineRepo.findAll();
		return airlines;
	}

	public List<Airline> getAirlineByName(String name) {
		List<Airline> airlines = this.airlineRepo.findByName(name);
		return airlines;
	}

	public List<Flight> getFlights() {
		List<Flight> flights = this.flightRepo.findAll();
		return flights;
	}

	public List<Flight> getFlightByName(String name, String number, String model) {
		List<Flight> flights = this.flightRepo.findbyName(name, number, model);
		return flights;
	}

	public List<Flight> getFlightByLoc(String from, String to) {
		List<Flight> flights = this.flightRepo.findbyLoc(from, to);
		return flights;
	}

	public List<Discount> getDiscounts() {
		List<Discount> discounts = this.discountRepo.findAll();
		return discounts;
	}

	public Flight saveFlight(Flight flight) {
		return this.flightRepo.save(flight);
	}

	public void deleteFlight(Long id) {
		this.flightRepo.deleteById(id);
	}

	public Discount saveDiscount(Discount discount) {
		return this.discountRepo.save(discount);
	}

	public void deleteDiscount(Long id) {
		this.discountRepo.deleteById(id);
	}

	public List<Discount> getDiscountByCode(String code) {
		return this.discountRepo.findByCode(code);
	}

	public List<String> getFlightLocs(String locType, String loc) {
		List<String> flights = Collections.emptyList();
		if (locType.equalsIgnoreCase("from"))
			flights = this.flightRepo.findFromLocs(loc.equals("@")?"%":loc);
		if (locType.equalsIgnoreCase("to"))
			flights = this.flightRepo.findToLocs(loc.equals("@")?"%":loc);
		return flights;
	}
}
