package com.ticketbooking.service;

import com.ticketbooking.model.Customer;
import java.util.List;

public interface ICustomerService {
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer deleteCustomer(Customer customer);
    Customer viewCustomer(Integer custId);
    List<Customer> viewAllCustomers(Integer movieId);
}
