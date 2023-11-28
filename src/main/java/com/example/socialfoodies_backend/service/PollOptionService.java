package com.example.socialfoodies_backend.service;

import com.example.socialfoodies_backend.model.CustomerIceCream;
import com.example.socialfoodies_backend.model.IceCream;
import com.example.socialfoodies_backend.model.PollOption;
import com.example.socialfoodies_backend.repository.PollOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollOptionService {

    @Autowired
    private PollOptionsRepository pollOptionsRepository;

    public void createPollOptions(List<IceCream> iceCreams, List<CustomerIceCream> customerIceCreams) {
        for (IceCream iceCream : iceCreams) {
            PollOption iceCreamOption = new PollOption();
            iceCreamOption.setIceCream(iceCream);
            pollOptionsRepository.save(iceCreamOption);
        }

        for (CustomerIceCream customerIceCream : customerIceCreams) {
            PollOption customerIceCreamOption = new PollOption();
            customerIceCreamOption.setCustomerIceCream(customerIceCream);
            pollOptionsRepository.save(customerIceCreamOption);
        }
    }

}
