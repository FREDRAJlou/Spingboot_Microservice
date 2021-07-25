package com.flightBooking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightBooking.entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query(value = "SELECT f FROM Flight f where f.airline.name LIKE COALESCE(:name,'%') AND " + 
			"f.number LIKE COALESCE(:number,'%') AND f.model LIKE COALESCE(:model,'%')")
	List<Flight> findbyName( @Param("name") String name, @Param("number") String number, @Param("model") String model);

	@Query(value = "SELECT f FROM Flight f where f.from LIKE COALESCE(:from,'%') AND " + 
			"f.to LIKE COALESCE(:to,'%')")
	List<Flight> findbyLoc(@Param("from") String from,@Param("to") String to);
	
	@Query(value = "SELECT f.from FROM Flight f where f.to LIKE COALESCE(:loc,'%')")
	List<String> findFromLocs(@Param("loc") String loc);
	
	@Query(value = "SELECT f.to FROM Flight f where f.from LIKE COALESCE(:loc,'%')")
	List<String> findToLocs(@Param("loc") String loc);

}
