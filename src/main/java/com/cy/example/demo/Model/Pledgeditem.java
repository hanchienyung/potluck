package com.cy.example.demo.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pledgeditem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String itemname;

    private String itemtype;

    private String username;

    private String serving;

    @ManyToMany(mappedBy="userDish")
    private List<AppUser> dishbyUser;

}
