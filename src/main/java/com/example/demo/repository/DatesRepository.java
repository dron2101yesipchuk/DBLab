package com.example.demo.repository;

import com.example.demo.model.Buyer;
import com.example.demo.model.DatesOfOrderingAndReceiving;
import org.springframework.data.repository.CrudRepository;

public interface DatesRepository extends CrudRepository<DatesOfOrderingAndReceiving, Integer> {
}
