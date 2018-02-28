package com.cy.example.demo.setup;


import com.cy.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    AppUserRepository appuserRepository;

    @Autowired
    AppRoleRepository approleRepository;

    @Autowired
    PledgedItemRepository pledgedItemRepository;




    //@Autowired
    //RequiredskillRepository requiredskillRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data...");

        AppRole myUserRole = new AppRole("USER");
        approleRepository.save(myUserRole);

        AppRole myAdminRole = new AppRole("ADMIN");
        approleRepository.save(myAdminRole);

        // Add user roles
        AppUser user1 = new AppUser("bob@burger.com", "password", "Bobby", "Burger", "burgerb");
        user1.addRole(myUserRole);

        appuserRepository.save(user1);



        AppUser user2 = new AppUser("jane@virgin.com", "password", "Jane", "Virgin", "virginj");
        user2.addRole(myUserRole);
        appuserRepository.save(user2);

        AppUser user3 = new AppUser("mike@smith.com", "password", "Mike", "Smith", "smithm");
        user3.addRole(myUserRole);
        appuserRepository.save(user3);

        AppUser user4 = new AppUser("rod@williams.com", "password", "Rod", "Williams", "williamsrod");
        user4.addRole(myUserRole);
        appuserRepository.save(user4);

        AppUser user5 = new AppUser("larry@black.com", "password", "Larry", "Black", "blackl");
        user5.addRole(myAdminRole);
        appuserRepository.save(user5);


        PledgedItem pledgeditem1 = new PledgedItem();
        pledgeditem1.setItemName("chicken");
        pledgeditem1.setItemType("food");
        pledgeditem1.setServing("10");
        pledgedItemRepository.save(pledgeditem1);

        user1.addPledgedItem(pledgeditem1);
        appuserRepository.save(user1);

        PledgedItem pledgeditem2 = new PledgedItem();
        pledgeditem2.setItemName("noodles");
        pledgeditem2.setItemType("food");
        pledgeditem2.setServing("8");
        pledgedItemRepository.save(pledgeditem2);

        user2.addPledgedItem(pledgeditem2);
        appuserRepository.save(user2);


        PledgedItem pledgeditem3 = new PledgedItem();
        pledgeditem3.setItemName("coke");
        pledgeditem3.setItemType("drink");
        pledgeditem3.setServing("20");
        pledgedItemRepository.save(pledgeditem3);

        user3.addPledgedItem(pledgeditem3);
        appuserRepository.save(user3);

        PledgedItem pledgeditem4 = new PledgedItem();
        pledgeditem4.setItemName("cheesecake");
        pledgeditem4.setItemType("dessert");
        pledgeditem4.setServing("15");
        pledgedItemRepository.save(pledgeditem4);

        user4.addPledgedItem(pledgeditem4);
        appuserRepository.save(user4);

    }
}