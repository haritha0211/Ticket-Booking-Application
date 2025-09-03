package com.ticketbooking.controller;

import com.ticketbooking.model.Customer;
import com.ticketbooking.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) Integer movieId) {
        if (movieId != null) {
            return ResponseEntity.ok(customerService.viewAllCustomers(movieId));
        }
        // else get all if you have such a method, otherwise return empty
        return ResponseEntity.ok(List.of());
    }

    @PostMapping("/")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        customer.setCustomerId(id);
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) {
        Customer customer = customerService.viewCustomer(id);
        return ResponseEntity.ok(customerService.deleteCustomer(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.viewCustomer(id));
    }
}
