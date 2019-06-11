package com.customerService.CustomerInfo.controller;

import com.customerService.CustomerInfo.exception.CustomerNotFoundException;
import com.customerService.CustomerInfo.model.Customer;
import com.customerService.CustomerInfo.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;

	// Get All Customers
	@GetMapping("/allcustomers")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();

	}

	// Create a new Customer
	@PostMapping("/addcustomer")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	// Get a Single Customer using EmailId
	@GetMapping("/customer/email/{email}")
	public Optional<Customer> getByemailId(@PathVariable(value = "email") String email) {

		Optional<Customer> customer = customerRepository.findByemail(email);

		return customerRepository.findByemail(email);
	}

	// Get a Single Customer using phoneNumber
	@GetMapping("/customer/phonenumber/{phoneNumber}")
	public Optional<Customer> getByphone(@PathVariable(value = "phoneNumber") int phoneNumber) {

		Customer customer = customerRepository.findByphoneNumber(phoneNumber)
				.orElseThrow(() -> new CustomerNotFoundException("Customer", "phoneNumber", phoneNumber));

		return customerRepository.findByphoneNumber(phoneNumber);
	}

	// Update a Customer
	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	// Delete a CustomerById
	@DeleteMapping("/delete/{Id}")
	public void deleteCustomer(@PathVariable(value = "Id") int customerId) {
		customerRepository.deleteById(customerId);
	}
	
	@DeleteMapping("/deleteall")
	public void deleteallCustomer() {
		customerRepository.deleteAll();
	}

}
