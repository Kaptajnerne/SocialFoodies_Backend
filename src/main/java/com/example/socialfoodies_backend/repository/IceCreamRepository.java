package com.example.socialfoodies_backend.repository;

import com.example.socialfoodies_backend.model.IceCream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IceCreamRepository extends JpaRepository<IceCream, Integer> {
    IceCream findIceCreamByIceCreamID (int id);
}
