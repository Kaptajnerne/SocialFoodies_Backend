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

    public void createPollOptionsForIceCreams(List<IceCream> iceCreams) {
        for (IceCream iceCream : iceCreams) {
            PollOption option = new PollOption();
            option.setIceCream(iceCream);
            pollOptionsRepository.save(option);
        }
    }

    public void createPollOptionsForCustomerIceCreams(List<CustomerIceCream> customerIceCreams) {
        for (CustomerIceCream customerIceCream : customerIceCreams) {
            PollOption option = new PollOption();
            option.setCustomerIceCream(customerIceCream);
            pollOptionsRepository.save(option);
        }
    }

}
