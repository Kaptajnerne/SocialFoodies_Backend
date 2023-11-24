package com.example.socialfoodies_backend.controller;

import com.example.socialfoodies_backend.model.Customer;
import com.example.socialfoodies_backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping()
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }
}
