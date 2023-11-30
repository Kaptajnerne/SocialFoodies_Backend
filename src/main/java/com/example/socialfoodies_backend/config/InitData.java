package com.example.socialfoodies_backend.config;

import com.example.socialfoodies_backend.dto.VoteData;
import com.example.socialfoodies_backend.model.*;
import com.example.socialfoodies_backend.repository.*;
import com.example.socialfoodies_backend.service.PollOptionService;
import com.example.socialfoodies_backend.service.PollService;
import com.example.socialfoodies_backend.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitData implements CommandLineRunner {

    private final IceCreamRepository iceCreamRepository;
    private final CustomerIceCreamRepository customerIceCreamRepository;
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final PollService pollService;
    private final VoteService voteService;
    private final PollOptionService pollOptionService;

    public InitData(IceCreamRepository iceCreamRepository, CustomerIceCreamRepository customerIceCreamRepository, CustomerRepository customerRepository, AdminRepository adminRepository, PollService pollService, VoteService voteService, PollOptionService pollOptionService) {
        this.iceCreamRepository = iceCreamRepository;
        this.customerIceCreamRepository = customerIceCreamRepository;
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
        this.pollService = pollService;
        this.voteService = voteService;
        this.pollOptionService = pollOptionService;
    }


    @Override
    public void run(String... args) throws Exception {

        //Customers
        Customer customer = new Customer();
        customer.setEmail("Kjartan.leander@hotmail.com");
        customerRepository.save(customer);

        //Admins
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
            iceCream.setImageUrl("https://assets-global.website-files.com/610ad3ad234b4037b59c37dd/64ed1da028df7105ee810e07_Rocky%20Road%20Strawberry%2016L%20Scoop.png");
            iceCreamRepository.save(iceCream);
            iceCreams.add(iceCream);
        }

        //Customer ice creams
        List<CustomerIceCream> customerIceCreams = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CustomerIceCream cIceCream = new CustomerIceCream();
            cIceCream.setName("Customer Ice Cream " + i);
            cIceCream.setDescription("Customer Description " + i);
            cIceCream.setNuts(false);
            cIceCream.setVegan(true);
            cIceCream.setCustomer(customer);
            cIceCream.setImageUrl("https://assets-global.website-files.com/610ad3ad234b4037b59c37dd/6168de16495ee0dd4d5dec0c_DFVANILLA_SCOOPSIMAGE_1200x800%20(1).png");
            customerIceCreamRepository.save(cIceCream);
            customerIceCreams.add(cIceCream);
        }

        //Poll options
        pollOptionService.createPollOptions(iceCreams, customerIceCreams);

        //Poll
        int[] pollOptionIds = {1, 3, 5};
        Poll poll = pollService.createAndSetupPoll(LocalDate.now(), LocalDate.now().plusDays(30), pollOptionIds);

        //Vote
        VoteData voteData1 = new VoteData(1, customer.getEmail());
        voteService.castVotesAndUpdatePollOptions(poll, voteData1);

        VoteData voteData2 = new VoteData(1, "kjartan@gmail.com");
        voteService.castVotesAndUpdatePollOptions(poll, voteData2);

        VoteData voteData3 = new VoteData(5, "Diego@gmail.com");
        voteService.castVotesAndUpdatePollOptions(poll, voteData3);
    }
}
