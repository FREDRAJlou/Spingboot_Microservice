package com.flightBooking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightBooking.entities.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

	List<Airline> findByName(String name);

}
