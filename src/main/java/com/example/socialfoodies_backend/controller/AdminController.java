package com.example.socialfoodies_backend.controller;

import com.example.socialfoodies_backend.model.Admin;
import com.example.socialfoodies_backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping()
    public ResponseEntity<List<Admin>> findAll() {
        List<Admin> admins = adminRepository.findAll();
        return ResponseEntity.ok().body(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> findById(@PathVariable int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Admin> create(@RequestBody Admin admin) {
        Admin createdAdmin = adminRepository.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }
}
