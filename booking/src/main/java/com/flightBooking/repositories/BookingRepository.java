package com.flightBooking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightBooking.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	public List<Booking> findByPnr(String pnr);

	public List<Booking> findByBooker(String name);

}
