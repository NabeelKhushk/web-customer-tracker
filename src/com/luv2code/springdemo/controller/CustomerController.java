package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// need to inject customer service
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customer from the service
		List<Customer> theCustomer = customerService.getCustomers();
		
		// add the customer to the model
		theModel.addAttribute("customers",theCustomer);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, 
									Model theModel){
		// get customer
		Customer theCustomer = customerService.getCustomers(theId);
		//set customer as a model
		theModel.addAttribute("customer", theCustomer);
		
		// send to form
		return "customer-form";
	}
	
	@GetMapping("/customerDeleteRow")
	public String customerDeleteRow(@RequestParam("customerId") int theId){
		// get customer
		customerService.deleteCustomer(theId);
		
		// send to form
		return "redirect:/customer/list";
	}
	
	
	
	
}





