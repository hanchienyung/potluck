package com.cy.example.demo.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class PledgedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String itemName;

    private String itemType;

    private String serving;

    @ManyToMany(mappedBy="pledgedItems", fetch = FetchType.LAZY)
    private Set<AppUser> users;


    public PledgedItem() {
        this.users = new HashSet<>();
    }

    public PledgedItem(String itemName, String itemType, String serving, Set<AppUser> users) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.serving = serving;
        this.users = new HashSet<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }
}
