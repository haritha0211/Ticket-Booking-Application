package com.ticketbooking.service.impl;

import com.ticketbooking.model.Customer;
import com.ticketbooking.repository.ICustomerRepository;
import com.ticketbooking.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer addCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer = customerRepository.findById(customer.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setMobileNumber(customer.getMobileNumber());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
        return customer;
    }

    @Override
    public Customer viewCustomer(Integer custId) {
        return customerRepository.findById(custId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public List<Customer> viewAllCustomers(Integer movieId) {
        return customerRepository.findCustomersByMovieId(movieId);
    }
}
