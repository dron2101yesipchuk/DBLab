package com.example.demo.repository;

import com.example.demo.model.BuyersHasMedicines;
import org.springframework.data.repository.CrudRepository;

public interface BuyerHasMedicinesRepository extends CrudRepository<BuyersHasMedicines, Integer> {
}
