package com.customerService.CustomerInfo.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.customerService.CustomerInfo.model.Customer;
import com.customerService.CustomerInfo.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerControllerTest {

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Mock
	CustomerRepository customerRepository;
	@Mock
	Customer customer;
	@InjectMocks
	CustomerController customerController;

	private MockMvc mockMvc;

	@Test
	public void createCustomerTest() throws Exception {
		Customer customerdata = new Customer(9, "sameer@gmail.com", 23456, "sameer", "sheikh");

		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(customerdata);
		Mockito.when(customerController.createCustomer(Mockito.any(Customer.class))).thenReturn(customerdata);
		mockMvc.perform(post("/addcustomer").contentType(MediaType.APPLICATION_JSON).content(jsonStr))
				.andExpect(status().isOk());
	}

	@Test
	public void getCustomersTest() throws Exception {
		Customer mockResponse = new Customer(10, "sushma@gmail.com", 4565, "sushma", "swaraj");
		Customer mockResponse1 = new Customer(11, "suma@gmail.com", 4485, "suma", "raj");
		List<Customer> customerlist = new ArrayList<Customer>();
		customerlist.add(mockResponse);
		customerlist.add(mockResponse1);
		Mockito.when(customerRepository.findAll()).thenReturn(customerlist);
		Mockito.when(customerController.getAllCustomers()).thenReturn(customerlist);

		MvcResult result = mockMvc.perform(get("/allcustomers").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		System.out.println("result for Get all customers " + result.getResponse().getContentAsString());
		// customerContorller.allCustomers();

	}

	@Test
	public void deleteCustomerByIdTest() throws Exception {
		Customer mockResponse = new Customer(9, "sameer@gmail.com", 23456, "sameer", "sheikh");

		customerController.deleteCustomer(9);

		verify(customerRepository, times(1)).deleteById(9);
		MvcResult result = mockMvc.perform(delete("/delete/9").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		System.out.println("result for delete " + result.getResponse().getContentAsString());
	}

	/*
	 * @Test public void getCustomerByEmailTest() throws Exception { List<Customer>
	 * list = new ArrayList<>(); Customer mockResponse1 = new Customer(11,
	 * "suma@gmail.com", 4485, "suma", "raj"); list.add(mockResponse1);
	 * Mockito.when(customerRepository.findByemail("suma@gmail.com")).thenReturn(
	 * Optional.of(list));
	 * //Mockito.when(customerController.getByemailId("swati@gmail.com")).thenReturn
	 * (list);
	 * 
	 * MvcResult result = mockMvc
	 * .perform(get("/customer/email/suma@gmail.com").contentType(MediaType.
	 * APPLICATION_JSON_UTF8)) .andReturn();
	 * System.out.println("result for Get customer by email " +
	 * result.getResponse().getContentAsString()); //
	 * customerContorller.allCustomers();
	 * 
	 * }
	 */
	
	@Test
	public void getCustomerByemailTest() throws Exception {
		List<Customer> list = new ArrayList<>();

		Customer mockResponse1 = new Customer(11, "suman@gmail.com", 4485, "suma", "raj");
		list.add(mockResponse1);

		//Mockito.when(customerController.getByphone(4485)).thenReturn(list);
		Mockito.when(customerRepository.findByemail("suman@gmail.com")).thenReturn(Optional.of(list));

		MvcResult result = mockMvc
				.perform(get("/customer/email/suman@gmail.com").contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		System.out.println("result for Get customer by mail " + result.getResponse().getContentAsString());
		// customerContorller.allCustomers();

	}
	@Test
	public void getCustomerByPhoneNoTest() throws Exception {
		List<Customer> list = new ArrayList<>();

		Customer mockResponse1 = new Customer(11, "suma@gmail.com", 4485, "suma", "raj");
		list.add(mockResponse1);

		//Mockito.when(customerController.getByphone(4485)).thenReturn(list);
		Mockito.when(customerRepository.findByphoneNumber(4485)).thenReturn(Optional.of(list));

		MvcResult result = mockMvc
				.perform(get("/customer/phonenumber/4485").contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		System.out.println("result for Get customer by phone " + result.getResponse().getContentAsString());
		// customerContorller.allCustomers();

	}

}