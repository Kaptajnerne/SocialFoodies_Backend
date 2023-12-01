package com.example.socialfoodies_backend.controller;

import com.example.socialfoodies_backend.model.CustomerIceCream;
import com.example.socialfoodies_backend.model.IceCream;
import com.example.socialfoodies_backend.repository.CustomerIceCreamRepository;
import com.example.socialfoodies_backend.repository.IceCreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/iceCreams")
@CrossOrigin
public class IceCreamController {

    @Autowired
    private IceCreamRepository iceCreamRepository;

    @GetMapping()
    public ResponseEntity<List<IceCream>> findAll() {
        List<IceCream> iceCreams = iceCreamRepository.findAll();
        return ResponseEntity.ok().body(iceCreams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IceCream> findById(@PathVariable int id) {
        Optional<IceCream> iceCream = iceCreamRepository.findById(id);
        return iceCream.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<IceCream> create(@RequestBody IceCream iceCream) {
        IceCream createdIceCream = iceCreamRepository.save(iceCream);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIceCream);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IceCream> update(@PathVariable int id, @RequestBody IceCream updatedIceCream) {
        Optional<IceCream> existingIceCream = iceCreamRepository.findById(id);
        if (existingIceCream.isPresent()) {
            updatedIceCream.setIceCreamID(id); //Make sure it's the right id
            IceCream savedIceCream = iceCreamRepository.save(updatedIceCream);
            return ResponseEntity.ok().body(savedIceCream);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

