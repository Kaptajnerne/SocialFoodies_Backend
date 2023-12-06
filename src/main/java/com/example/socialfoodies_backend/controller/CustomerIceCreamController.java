package com.example.socialfoodies_backend.controller;

import com.example.socialfoodies_backend.model.Customer;
import com.example.socialfoodies_backend.model.CustomerIceCream;
import com.example.socialfoodies_backend.model.IceCream;
import com.example.socialfoodies_backend.repository.CustomerIceCreamRepository;
import com.example.socialfoodies_backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customerIceCreams")
@CrossOrigin
public class CustomerIceCreamController {

    @Autowired
    CustomerIceCreamRepository customerIceCreamRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping()
    public ResponseEntity<List<CustomerIceCream>> findAll() {
        List<CustomerIceCream> customerIceCreams = customerIceCreamRepository.findAll();
        return ResponseEntity.ok().body(customerIceCreams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerIceCream> findById(@PathVariable int id) {
        Optional<CustomerIceCream> customerIceCream = customerIceCreamRepository.findById(id);
        return customerIceCream.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("{customerID}")
    public ResponseEntity<CustomerIceCream> create(@RequestBody CustomerIceCream customerIceCream, @PathVariable int customerID) {
        Customer customer = customerRepository.findByCustomerID(customerID);
        customerIceCream.setCustomer(customer);
        CustomerIceCream createdCustomerIceCream = customerIceCreamRepository.save(customerIceCream);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerIceCream);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerIceCream> update(@PathVariable int id, @RequestBody CustomerIceCream updatedCustomerIceCream) {
        Optional<CustomerIceCream> existingCustomerIceCream = customerIceCreamRepository.findById(id);
        if (existingCustomerIceCream.isPresent()) {
            updatedCustomerIceCream.setCustomerIceCreamID(id); //Make sure it's the right id
            CustomerIceCream savedCustomerIceCream = customerIceCreamRepository.save(updatedCustomerIceCream);
            return ResponseEntity.ok().body(savedCustomerIceCream);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerIceCream(@PathVariable int id) {
        Optional<CustomerIceCream> customerIceCream = customerIceCreamRepository.findById(id);
        if (customerIceCream.isPresent()) {
            customerIceCreamRepository.delete(customerIceCream.get());
            return ResponseEntity.ok("Customer ice cream deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
