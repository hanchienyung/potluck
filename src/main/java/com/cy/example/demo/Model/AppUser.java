package com.cy.example.demo.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USER_DATA")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;


    @Column(name="username")
    private String username;


    //use Fetch Type Eager user all data will be avivalible for this object
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))


   // private Collection<com.cy.example.demo.Models.Role> roles;
    private Set<AppRole> roles;



    @ManyToMany(mappedBy = "users")
    private Set<PledgedItem> pledgedItems;

    public AppUser() {
        this.roles = new HashSet<>();
        this.pledgedItems = new HashSet();
    }

    public AppUser(String email, String password, String firstName, String lastName, String username) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.roles = new HashSet<>();
        this.pledgedItems = new HashSet<>();
    }


    public void addRole(AppRole role) { this.roles.add(role);}


    public void addPledgedItem(PledgedItem pledgedItem) {
        this.pledgedItems.add(pledgedItem);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    public Set<PledgedItem> getPledgedItems() {
        return pledgedItems;
    }

    public void setPledgedItems(Set<PledgedItem> pledgedItems) {
        this.pledgedItems = pledgedItems;
    }
}

