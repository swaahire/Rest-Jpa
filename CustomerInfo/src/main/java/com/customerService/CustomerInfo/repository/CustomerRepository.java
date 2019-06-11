package com.customerService.CustomerInfo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.customerService.CustomerInfo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query(value = "SELECT * FROM customer WHERE email=?1", nativeQuery = true)
	Optional<Customer> findByemail(String email);

	@Query(value = "SELECT * FROM customer WHERE phone_number=?1", nativeQuery = true)
	Optional<Customer> findByphoneNumber(int phoneNumber);

}
