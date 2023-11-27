package com.example.socialfoodies_backend.config;

import com.example.socialfoodies_backend.model.*;
import com.example.socialfoodies_backend.repository.*;
import com.example.socialfoodies_backend.service.PollService;
import com.example.socialfoodies_backend.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    IceCreamRepository iceCreamRepository;

    @Autowired
    CustomerIceCreamRepository customerIceCreamRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PollRepository pollRepository;

    @Autowired
    PollOptionsRepository pollOptionsRepository;

    @Autowired
    PollService pollService;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    VoteService voteService;


    @Override
    public void run(String... args) throws Exception {

        // Customers
        Customer customer = new Customer();
        customer.setEmail("Kjartan.leander@hotmail.com");
        customerRepository.save(customer);

        // Admins
        Admin admin = new Admin();
        admin.setEmail("Simon.Bang@hotmail.com");
        admin.setPassword("Simon25");
        adminRepository.save(admin);

        //Ice cream
        List<IceCream> iceCreams = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            IceCream iceCream = new IceCream();
            iceCream.setName("Ice Cream " + i);
            iceCream.setDescription("Description " + i);
            iceCream.setNuts(true);
            iceCream.setVegan(false);
            iceCreamRepository.save(iceCream);
            iceCreams.add(iceCream);
        }

        // Customer ice creams
        List<CustomerIceCream> customerIceCreams = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            CustomerIceCream cIceCream = new CustomerIceCream();
            cIceCream.setName("Customer Ice Cream " + i);
            cIceCream.setDescription("Customer Description " + i);
            cIceCream.setNuts(false);
            cIceCream.setVegan(true);
            cIceCream.setCustomer(customer);
            customerIceCreamRepository.save(cIceCream);
            customerIceCreams.add(cIceCream);
        }

        // Poll options
        for (IceCream iceCream : iceCreams) {
            PollOption iceCreamOption = new PollOption();
            iceCreamOption.setIceCream(iceCream);
            pollOptionsRepository.save(iceCreamOption);
        }

        for (CustomerIceCream cIceCream : customerIceCreams) {
            PollOption customerIceCreamOption = new PollOption();
            customerIceCreamOption.setCustomerIceCream(cIceCream);
            pollOptionsRepository.save(customerIceCreamOption);
        }

        //Poll
        Poll poll = new Poll();
        poll.setStartDate(LocalDate.now());
        poll.setEndDate(LocalDate.now().plusDays(30));
        poll.setPollOptions(pollService.selectPollOptions(1,3,5));
        pollRepository.save(poll);

        if (poll != null) {
            for (PollOption selectedPollOptions : poll.getPollOptions()) {
                selectedPollOptions.setPoll(poll);
                pollOptionsRepository.save(selectedPollOptions);
            }
        }

        //Vote
        Vote vote1 = voteService.castVote(1, customer.getEmail());
        voteRepository.save(vote1);

        Vote vote2 = voteService.castVote(3, "kjartan@gmail.com");
        voteRepository.save(vote2);

        Vote vote3 = voteService.castVote(5, "Diego@gmail.com");
        voteRepository.save(vote3);
    }
}
