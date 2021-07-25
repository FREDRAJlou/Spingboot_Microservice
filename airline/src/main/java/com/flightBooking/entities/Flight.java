package com.flightBooking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "flight")
@NamedQueries({
		@NamedQuery(name = "getFlightByname", query = "SELECT f FROM Flight f where f.airline.name LIKE COALESCE(:name,'%') AND "
				+ "f.number LIKE COALESCE(:number,'%') AND f.model LIKE COALESCE(:model,'%')") })
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "flightNumber")
	private String number;
	private String logo;
	@Column(name = "fromLocation")
	private String from;
	@Column(name = "toLocation")
	private String to;
	@Column(name = "flightSchedule")
	private String schedule;
	private String model;
	private Long price;
	@ManyToOne
	@JoinColumn(name = "airlineId", nullable = false, insertable = true, updatable = false)
	@JsonIgnoreProperties("flights")
	private Airline airline;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Flight() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Airline getAirline() {
		return airline;
	}

}
