package com.cy.example.demo.Model;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findUserByUsername(String username);
}