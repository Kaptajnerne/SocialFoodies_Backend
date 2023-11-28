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
        IceCream iceCream1 = new IceCream();
        iceCream1.setName("Vanille");
        iceCream1.setDescription("Smager af vanillie");
        iceCream1.setNuts(true);
        iceCream1.setVegan(false);
        iceCream1.setMovieImageUrl("https://kastbergs.dk/wp-content/uploads/ny_vanilje_ccexpress.png");
        iceCreamRepository.save(iceCream1);
        iceCreams.add(iceCream1);

        IceCream iceCream2 = new IceCream();
        iceCream2.setName("Chokolade");
        iceCream2.setDescription("Smager af chokolade");
        iceCream2.setNuts(true);
        iceCream2.setVegan(false);
        iceCream2.setMovieImageUrl("https://paradis-is.dk/wp-content/uploads/2018/07/chokolade-is-kugle.png");
        iceCreamRepository.save(iceCream2);
        iceCreams.add(iceCream2);

        IceCream iceCream3 = new IceCream();
        iceCream3.setName("Mango");
        iceCream3.setDescription("Smager af mango");
        iceCream3.setNuts(true);
        iceCream3.setVegan(false);
        iceCream3.setMovieImageUrl("https://kastbergs.dk/wp-content/uploads/kastbergs_mango_sorbet_optimized-1_ccexpress-1.png");
        iceCreamRepository.save(iceCream3);
        iceCreams.add(iceCream3);

        IceCream iceCream4 = new IceCream();
        iceCream4.setName("Jordbær");
        iceCream4.setDescription("Smager af jordbær");
        iceCream4.setNuts(true);
        iceCream4.setVegan(false);
        iceCream4.setMovieImageUrl("https://paradis-is.dk/wp-content/uploads/2018/07/jordbaer-champagne-is-kugle-300x230.png");
        iceCreamRepository.save(iceCream4);
        iceCreams.add(iceCream4);

        IceCream iceCream5 = new IceCream();
        iceCream5.setName("Ananas");
        iceCream5.setDescription("Smager af ananas");
        iceCream5.setNuts(true);
        iceCream5.setVegan(false);
        iceCream5.setMovieImageUrl("https://robertsisbar.dk/wp-content/uploads/2017/07/ananas.png");
        iceCreamRepository.save(iceCream5);
        iceCreams.add(iceCream5);

        IceCream iceCream6 = new IceCream();
        iceCream6.setName("Banan");
        iceCream6.setDescription("Smager af banan");
        iceCream6.setNuts(true);
        iceCream6.setVegan(false);
        iceCream6.setMovieImageUrl("https://robertsisbar.dk/wp-content/uploads/2017/07/banan.png");
        iceCreamRepository.save(iceCream6);
        iceCreams.add(iceCream6);

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
