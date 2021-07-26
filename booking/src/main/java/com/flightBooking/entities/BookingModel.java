package com.flightBooking.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class BookingModel {
	private Long id;

	private String pnr;
	private String type;
	private Long price;
	private String from;
	private String to;
	private String flight;
	private String returnFlight;
	private Date onwardDate;
	private Date returnDate;
	private String booker;
	@Column(name = "travelDate")
	private Date date;
	private boolean active;

	private List<Passenger> passengers;

	public BookingModel() {

	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public Long getPrice() {
		return price;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getFlight() {
		return flight;
	}

	public String getReturnFlight() {
		return returnFlight;
	}

	public Date getOnwardDate() {
		return onwardDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public String getBooker() {
		return booker;
	}

	public Date getDate() {
		return date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public void setReturnFlight(String returnFlight) {
		this.returnFlight = returnFlight;
	}

	public void setOnwardDate(Date onwardDate) {
		this.onwardDate = onwardDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public void setBooker(String booker) {
		this.booker = booker;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", pnr=" + pnr + ", type=" + type + ", price=" + price + ", from=" + from + ", to="
				+ to + ", flight=" + flight + ", returnFlight=" + returnFlight + ", onwardDate=" + onwardDate
				+ ", returnDate=" + returnDate + ", booker=" + booker + ", date=" + date + ", active=" + active
				+ ", passengers=" + passengers + "]";
	}

}
