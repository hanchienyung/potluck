package com.cy.example.demo.Model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PledgedItemRepository extends CrudRepository<PledgedItem, Long> {
    List<PledgedItem> findAll() ;
    List<PledgedItem> findAllByItemName(String itemname);
}