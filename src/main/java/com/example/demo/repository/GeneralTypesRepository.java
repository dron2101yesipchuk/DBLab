package com.example.demo.repository;

import com.example.demo.model.Buyer;
import com.example.demo.model.GeneralTypeOfMedicine;
import org.springframework.data.repository.CrudRepository;

public interface GeneralTypesRepository extends CrudRepository<GeneralTypeOfMedicine, Integer> {
}
