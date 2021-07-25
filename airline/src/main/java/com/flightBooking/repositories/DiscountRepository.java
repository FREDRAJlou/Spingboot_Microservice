package com.flightBooking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightBooking.entities.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

	List<Discount> findByCode(String code);

}
