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
    private final IceCreamSuggestionRepository iceCreamSuggestionRepository;

    public InitData(IceCreamRepository iceCreamRepository, CustomerIceCreamRepository customerIceCreamRepository, CustomerRepository customerRepository, AdminRepository adminRepository, PollService pollService, VoteService voteService, PollOptionService pollOptionService, IceCreamSuggestionRepository iceCreamSuggestionRepository) {
        this.iceCreamRepository = iceCreamRepository;
        this.customerIceCreamRepository = customerIceCreamRepository;
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
        this.pollService = pollService;
        this.voteService = voteService;
        this.pollOptionService = pollOptionService;
        this.iceCreamSuggestionRepository = iceCreamSuggestionRepository;
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

        //Admins
        Admin test = new Admin();
        admin.setEmail("x");
        admin.setPassword("x");
        adminRepository.save(admin);

        //Suggestions
        IceCreamSuggestion iceCreamSuggestion1 = new IceCreamSuggestion();
        iceCreamSuggestion1.setName("Kiwi-Mango Magic");
        iceCreamSuggestion1.setDescription("Taste of summer");
        iceCreamSuggestion1.setNuts(true);
        iceCreamSuggestion1.setVegan(true);
        iceCreamSuggestion1.setCustomer(customer);
        iceCreamSuggestionRepository.save(iceCreamSuggestion1);

        IceCreamSuggestion iceCreamSuggestion2 = new IceCreamSuggestion();
        iceCreamSuggestion2.setName("Cacti sorbet");
        iceCreamSuggestion2.setDescription("Taste of desert");
        iceCreamSuggestion2.setNuts(false);
        iceCreamSuggestion2.setVegan(true);
        iceCreamSuggestion2.setCustomer(customer);
        iceCreamSuggestionRepository.save(iceCreamSuggestion2);

        IceCreamSuggestion iceCreamSuggestion3 = new IceCreamSuggestion();
        iceCreamSuggestion3.setName("We Go Gym");
        iceCreamSuggestion3.setDescription("Taste of sweat");
        iceCreamSuggestion3.setNuts(false);
        iceCreamSuggestion3.setVegan(false);
        iceCreamSuggestion3.setCustomer(customer);
        iceCreamSuggestionRepository.save(iceCreamSuggestion3);

        IceCreamSuggestion iceCreamSuggestion4 = new IceCreamSuggestion();
        iceCreamSuggestion4.setName("PB&J");
        iceCreamSuggestion4.setDescription("Taste of freedom");
        iceCreamSuggestion4.setNuts(true);
        iceCreamSuggestion4.setVegan(false);
        iceCreamSuggestion4.setCustomer(customer);
        iceCreamSuggestionRepository.save(iceCreamSuggestion4);

        //Ice cream
        List<IceCream> iceCreams = new ArrayList<>();
        IceCream iceCream1 = new IceCream();
        iceCream1.setName("Vanillje");
        iceCream1.setDescription("Smager af vanilljie");
        iceCream1.setNuts(true);
        iceCream1.setVegan(false);
        iceCream1.setImageUrl("https://kastbergs.dk/wp-content/uploads/ny_vanilje_ccexpress.png");
        iceCreamRepository.save(iceCream1);
        iceCreams.add(iceCream1);

        IceCream iceCream2 = new IceCream();
        iceCream2.setName("Chokolade");
        iceCream2.setDescription("Smager af chokolade");
        iceCream2.setNuts(true);
        iceCream2.setVegan(false);
        iceCream2.setImageUrl("https://paradis-is.dk/wp-content/uploads/2018/07/chokolade-is-kugle.png");
        iceCreamRepository.save(iceCream2);
        iceCreams.add(iceCream2);

        IceCream iceCream3 = new IceCream();
        iceCream3.setName("Mango");
        iceCream3.setDescription("Smager af mango");
        iceCream3.setNuts(true);
        iceCream3.setVegan(false);
        iceCream3.setImageUrl("https://kastbergs.dk/wp-content/uploads/kastbergs_mango_sorbet_optimized-1_ccexpress-1.png");
        iceCreamRepository.save(iceCream3);
        iceCreams.add(iceCream3);

        IceCream iceCream4 = new IceCream();
        iceCream4.setName("Jordbær");
        iceCream4.setDescription("Smager af jordbær");
        iceCream4.setNuts(true);
        iceCream4.setVegan(false);
        iceCream4.setImageUrl("https://paradis-is.dk/wp-content/uploads/2018/07/jordbaer-champagne-is-kugle-300x230.png");
        iceCreamRepository.save(iceCream4);
        iceCreams.add(iceCream4);

        IceCream iceCream5 = new IceCream();
        iceCream5.setName("Ananas");
        iceCream5.setDescription("Smager af ananas");
        iceCream5.setNuts(true);
        iceCream5.setVegan(false);
        iceCream5.setImageUrl("https://robertsisbar.dk/wp-content/uploads/2017/07/ananas.png");
        iceCreamRepository.save(iceCream5);
        iceCreams.add(iceCream5);

        IceCream iceCream6 = new IceCream();
        iceCream6.setName("Banan");
        iceCream6.setDescription("Smager af banan");
        iceCream6.setNuts(true);
        iceCream6.setVegan(false);
        iceCream6.setImageUrl("https://robertsisbar.dk/wp-content/uploads/2017/07/banan.png");
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
            cIceCream.setImageUrl("https://assets-global.website-files.com/610ad3ad234b4037b59c37dd/6168de16495ee0dd4d5dec0c_DFVANILLA_SCOOPSIMAGE_1200x800%20(1).png");
            customerIceCreamRepository.save(cIceCream);
            customerIceCreams.add(cIceCream);
        }

        //Poll options
        pollOptionService.createPollOptions(iceCreams, customerIceCreams);

        //Poll
        int[] pollOptionIds = {1, 3, 5};
        LocalDate date = LocalDate.of(2023, 12, 5);
        Poll poll = pollService.createAndSetupPoll(date, date.plusDays(30), pollOptionIds);
        //Poll poll2 = pollService.createAndSetupPoll(LocalDate.now().plusDays(31), LocalDate.now().plusDays(60), pollOptionIds);

        //Vote
        VoteData voteData1 = new VoteData(1, customer.getEmail());
        voteService.castVotesAndUpdatePollOptions(poll, voteData1);

        VoteData voteData2 = new VoteData(1, "kjartan@gmail.com");
        voteService.castVotesAndUpdatePollOptions(poll, voteData2);

        VoteData voteData3 = new VoteData(5, "Diego@gmail.com");
        voteService.castVotesAndUpdatePollOptions(poll, voteData3);
    }
}
