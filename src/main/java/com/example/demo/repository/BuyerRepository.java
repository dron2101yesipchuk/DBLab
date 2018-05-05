package com.example.demo.repository;

import com.example.demo.model.Buyer;
import org.springframework.data.repository.CrudRepository;

public interface BuyerRepository extends CrudRepository<Buyer, Integer> {
}
