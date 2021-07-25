package com.flightBooking.tests;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.flightBooking.controller.AirlineController;
import com.flightBooking.entities.Airline;
import com.flightBooking.entities.Discount;
import com.flightBooking.entities.Flight;
import com.flightBooking.repositories.AirlineRepository;
import com.flightBooking.repositories.DiscountRepository;
import com.flightBooking.repositories.FlightRepository;

@SpringBootTest
class AirlineTests {

	@Autowired
	private AirlineController AirlineController;

	@MockBean
	private AirlineRepository repository;
	@MockBean
	private FlightRepository flightRepo;
	@MockBean
	private DiscountRepository discountRepo;

	@Test
	public void testAllAirlines() {
		Mockito.when(repository.findAll()).thenReturn(Arrays.asList(new Airline(), new Airline(), new Airline()));
		List<Airline> books = AirlineController.getAllAirlines();
		Assertions.assertSame(3, books.size());
	}

	@Test
	public void testAirlineDelete() {
		Long id = 12l;
		this.AirlineController.deleteAirline(id);
		Mockito.verify(repository, Mockito.times(1)).deleteById(id);
	}

	@Test
	public void testAirlineSave() {
		Airline Airline = new Airline();
		Mockito.when(repository.save(Airline)).thenReturn(Airline);
		Airline Airline1 = this.AirlineController.saveAirline(Airline);
		Assertions.assertEquals(Airline, Airline1);
	}

	@Test
	public void testAllFlights() {
		Mockito.when(this.flightRepo.findAll()).thenReturn(Arrays.asList(new Flight(), new Flight()));
		List<Flight> flights = AirlineController.getFlights();
		Assertions.assertSame(2, flights.size());
	}
	
	@Test
	public void testGetFlightByName() {
		String name="Indigo",number="IND123",model="AE09";
		Mockito.when(this.flightRepo.findbyName(name, number, model)).thenReturn(Arrays.asList(new Flight()));
		List<Flight> flights = AirlineController.getFlightByName("Indigo,IND123,AE09");
		Assertions.assertSame(1, flights.size());
	}

	@Test
	public void testFlightDelete() {
		Long id = 12l;
		this.AirlineController.deleteFlight(id);
		Mockito.verify(flightRepo, Mockito.times(1)).deleteById(id);
	}

	@Test
	public void testFlightSave() {
		Flight flight = new Flight();
		Mockito.when(flightRepo.save(flight)).thenReturn(flight);
		Flight flight1 = this.AirlineController.saveFlight(flight);
		Assertions.assertEquals(flight, flight1);
	}
	
	@Test
	public void testGetDiscounts() {
		Mockito.when(this.discountRepo.findAll()).thenReturn(Arrays.asList(new Discount(), new Discount()));
		List<Discount> discounts = AirlineController.getDiscounts();
		Assertions.assertSame(2, discounts.size());
	}
	
	@Test
	public void testGetDiscountByCode() {
		String code="FIRST50";
		Mockito.when(this.discountRepo.findByCode(code)).thenReturn(Arrays.asList(new Discount()));
		List<Discount> discounts = AirlineController.getDiscountByCode("FIRST50");
		Assertions.assertSame(1, discounts.size());
	}

	@Test
	public void testDiscountSave() {
		Discount discount = new Discount();
		Mockito.when(this.discountRepo.save(discount)).thenReturn(discount);
		Discount discount1 = this.AirlineController.saveDiscount(discount);
		Assertions.assertEquals(discount, discount1);
	}

}
