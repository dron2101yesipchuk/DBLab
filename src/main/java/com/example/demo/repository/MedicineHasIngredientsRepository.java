package com.example.demo.repository;

import com.example.demo.model.Buyer;
import com.example.demo.model.MedicineHasIngredients;
import org.springframework.data.repository.CrudRepository;

public interface MedicineHasIngredientsRepository extends CrudRepository<MedicineHasIngredients, Integer> {
}
