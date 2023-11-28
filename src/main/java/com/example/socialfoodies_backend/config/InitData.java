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
    private final PollRepository pollRepository;
    private final PollOptionsRepository pollOptionsRepository;
    private final PollService pollService;
    private final VoteRepository voteRepository;
    private final VoteService voteService;
    private final PollOptionService pollOptionService;

    public InitData(IceCreamRepository iceCreamRepository, CustomerIceCreamRepository customerIceCreamRepository, CustomerRepository customerRepository, AdminRepository adminRepository, PollRepository pollRepository, PollOptionsRepository pollOptionsRepository, PollService pollService, VoteRepository voteRepository, VoteService voteService, PollOptionService pollOptionService) {
        this.iceCreamRepository = iceCreamRepository;
        this.customerIceCreamRepository = customerIceCreamRepository;
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
        this.pollRepository = pollRepository;
        this.pollOptionsRepository = pollOptionsRepository;
        this.pollService = pollService;
        this.voteRepository = voteRepository;
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
            customerIceCreamRepository.save(cIceCream);
            customerIceCreams.add(cIceCream);
        }

        //Poll options
        pollOptionService.createPollOptions(iceCreams, customerIceCreams);

        //Poll
        int[] pollOptionIds = {1, 3, 5};
        Poll poll = pollService.createAndSetupPoll(LocalDate.now(), LocalDate.now().plusDays(30), pollOptionIds);

        //Vote
        List<VoteData> votesDataList = new ArrayList<>();
        votesDataList.add(new VoteData(1, customer.getEmail()));
        votesDataList.add(new VoteData(1, "kjartan@gmail.com"));
        votesDataList.add(new VoteData(5, "Diego@gmail.com"));
        voteService.castVotesAndUpdatePollOptions(poll, votesDataList);
    }
}
