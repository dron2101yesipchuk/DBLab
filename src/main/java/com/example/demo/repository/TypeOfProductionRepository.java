package com.example.demo.repository;

import com.example.demo.model.Buyer;
import com.example.demo.model.TypeOfProduction;
import org.springframework.data.repository.CrudRepository;

public interface TypeOfProductionRepository extends CrudRepository<TypeOfProduction, Integer> {
}
