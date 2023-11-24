package com.example.socialfoodies_backend.config;

import com.example.socialfoodies_backend.model.CustomerIceCream;
import com.example.socialfoodies_backend.model.IceCream;
import com.example.socialfoodies_backend.repository.CustomerIceCreamRepository;
import com.example.socialfoodies_backend.repository.IceCreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    IceCreamRepository iceCreamRepository;

    @Autowired
    CustomerIceCreamRepository customerIceCreamRepository;


    @Override
    public void run(String... args) throws Exception {


        //Ice creams
        IceCream iceCream1 = new IceCream();
        iceCream1.setName("Blueberry");
        iceCream1.setDescription("Taste of tiny smurfs");
        iceCream1.setNuts(true);
        iceCream1.setVegan(false);
        iceCreamRepository.save(iceCream1);

        //Customer ice creams
        CustomerIceCream customerIceCream = new CustomerIceCream();
        customerIceCream.setName("BananaMango");
        customerIceCream.setDescription("Taste of banana and mango");
        customerIceCream.setNuts(false);
        customerIceCream.setVegan(true);
        customerIceCreamRepository.save(customerIceCream);

    }
}
