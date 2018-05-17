package com.example.demo.controller;

import com.example.demo.model.Buyer;
import com.example.demo.model.BuyersHasMedicines;
import com.example.demo.model.MedicineHasIngredients;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    BuyerHasMedicinesRepository buyerHasMedicinesRepository;
    @Autowired
    DatesRepository datesRepository;
    @Autowired
    GeneralTypesRepository generalTypesRepository;
    @Autowired
    IngredientsRepository ingredientsRepository;
    @Autowired
    MedicineHasIngredientsRepository medicineHasIngredientsRepository;
    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    TypeOfMedicineHasTypeOfUsingRepository typeOfMedicineHasTypeOfUsingRepository;
    @Autowired
    TypeOfMedicineRepository typeOfMedicineRepository;
    @Autowired
    TypeOfProductionRepository typeOfProductionRepository;
    @Autowired
    TypeOfUsingRepository typeOfUsingRepository;

    @RequestMapping("/request1_1")
    List<BuyersHasMedicines> getBuyerThatDidntComeForMedicine(){
        return buyerHasMedicinesRepository.getBuyerThatDidntComeForMedicine();
    }

    @RequestMapping("/request1_2")
    Integer getAmountOfBuyerThatDidntComeForMedicine(){
        return buyerHasMedicinesRepository.getAmountOfBuyerThatDidntComeForMedicine();
    }

    @RequestMapping("/request2_list_all")
    List<BuyersHasMedicines> getAllBuyersThatWaitingForMedicines(){
        return buyerHasMedicinesRepository.getAllBuyersThatWaitingForMedicines();
    }

    @RequestMapping("/request2_amount_all")
    Integer getAmountOfAllBuyersThatWaitingForMedicines(){
        return buyerHasMedicinesRepository.getAmountOfAllBuyersThatWaitingForMedicines();
    }

    @RequestMapping("/request2_list_selected_type")
    List<BuyersHasMedicines> getBuyersThatWaitingForMedicinesOfSelectedType(@RequestParam String nameOfType){
        return buyerHasMedicinesRepository.
                getBuyersThatWaitingForMedicinesOfSelectedType(nameOfType);
    }

    @RequestMapping("/request2_amount_selected_type")
    Integer getAmountOfBuyersThatWaitingForMedicinesOfSelectedType(@RequestParam String nameOfType){
        return buyerHasMedicinesRepository.
                getAmountOfBuyersThatWaitingForMedicinesOfSelectedType(nameOfType);
    }

    @RequestMapping("/request3_all")
    List<BuyersHasMedicines> getAllTheMostPopularMedicines(){
        List<BuyersHasMedicines> allMostPopularMedicines =
                buyerHasMedicinesRepository.getAllTheMostPopularMedicines();
        for (int i = 0; i < allMostPopularMedicines.size(); i++)
            if(i>9) {
                allMostPopularMedicines.remove(i);
                i--;
            }
        return allMostPopularMedicines;
    }

    @RequestMapping("/request3_type")
    List<BuyersHasMedicines> getTheMostPopularMedicinesOfSelectedType(@RequestParam String nameOfType){
        List<BuyersHasMedicines> allMostPopularMedicines =
                buyerHasMedicinesRepository.getTheMostPopularMedicinesOfSelectedType(nameOfType);
        for (int i = 0; i < allMostPopularMedicines.size(); i++)
            if(i>9) {
                allMostPopularMedicines.remove(i);
                i--;
            }
        return allMostPopularMedicines;
    }

    @RequestMapping("/request4")
    List<MedicineHasIngredients> getVolumeOfIngredientsBetweenSelectedDates(
            @RequestParam Date firstDate, Date secondDate){
        return medicineHasIngredientsRepository.
                getVolumeOfIngredientsBetweenSelectedDates(firstDate, secondDate);
    }

    @RequestMapping("/request5_all_buyers_amount")
    Integer getAmountOfBuyersThatOrderSelectedMedicineBetweenSelectedDates
            (@RequestParam String nameOfMedicine,Date firstDate,Date secondDate){
        return buyerHasMedicinesRepository
                .getAmountOfBuyersThatOrderSelectedMedicineBetweenSelectedDates(
                        nameOfMedicine, firstDate, secondDate);
    }

    @RequestMapping("/request5_all_buyers_list")
    List<BuyersHasMedicines> getListOfBuyersThatOrderSelectedMedicineBetweenSelectedDates
            (@RequestParam String nameOfMedicine, Date firstDate, Date secondDate){
        return buyerHasMedicinesRepository.getListOfBuyersThatOrderSelectedMedicineBetweenSelectedDates(
                nameOfMedicine, firstDate, secondDate
        );
    }

    @RequestMapping("/request5_medicine_type_buyers_amount")
    Integer getAmountOfBuyersThatOrderSelectedTypeOfMedicineBetweenSelectedDates
            (@RequestParam String nameOfType, Date firstDate, Date secondDate){
        return buyerHasMedicinesRepository.getAmountOfBuyersThatOrderSelectedTypeOfMedicineBetweenSelectedDates(
                nameOfType, firstDate, secondDate
        );
    }

    @RequestMapping("/request5_medicine_type_buyers_list")
    List<BuyersHasMedicines> getListOfBuyersThatOrderSelectedTypeOfMedicineBetweenSelectedDates
            (@RequestParam String nameOfType, Date firstDate, Date secondDate){
        return buyerHasMedicinesRepository.getListOfBuyersThatOrderSelectedTypeOfMedicineBetweenSelectedDates(
                nameOfType, firstDate, secondDate
        );
    }

    @RequestMapping("/request8_amount")
    Integer getAmountOfOrdersThatAreMakingNow(){
        return buyerHasMedicinesRepository.getAmountOfOrdersThatAreMakingNow();
    }

    @RequestMapping("/request8_list")
    List<BuyersHasMedicines> getListOfOrdersThatAreMakingNow(){
        return buyerHasMedicinesRepository.getListOfOrdersThatAreMakingNow();
    }

    @RequestMapping("/request9_list")
    List<MedicineHasIngredients> getListOfIngredientsOfMedicinesThatAreMaking(){
        return medicineHasIngredientsRepository.getListOfIngredientsOfMedicinesThatAreMaking(
                "Виготовляється"
        );
    }

    @RequestMapping("/request9_amount")
    Integer getAmountOfIngredientsOfMedicinesThatAreMaking(){
        return medicineHasIngredientsRepository.getAmountOfIngredientsOfMedicinesThatAreMaking(
                "Виготовляється"
        );
    }

    @RequestMapping("/request10_made_medicines")
    List<MedicineHasIngredients> getListOfVolumeAndPriceOfIngredientsOfMedicinesThatMakeInDrugstore(
            @RequestParam String nameOfMedicine){
        return medicineHasIngredientsRepository.getListOfVolumeAndPriceOfIngredientsOfMedicinesThatMakeInDrugstore(
                nameOfMedicine);
    }

    @RequestMapping("/request11_all")
    List<BuyersHasMedicines> getListOfBuyersThatBuyMostOften(){
        List<BuyersHasMedicines> buyersHasMedicines =
                buyerHasMedicinesRepository.getListOfBuyersThatBuyMostOften() ;
        for (int i = 1; i < buyersHasMedicines.size(); i++){
            buyersHasMedicines.remove(i);
            i--;
        }
        return buyersHasMedicines;
    }

    @RequestMapping("/request11_medicine")
    List<BuyersHasMedicines> getListOfBuyersThatBuySelectedMedicineMostOften(
            @RequestParam String nameOfMedicine){
        List<BuyersHasMedicines> buyersHasMedicines =
                buyerHasMedicinesRepository.
                        getListOfBuyersThatBuySelectedMedicineMostOften(nameOfMedicine);
        for (int i = 1; i < buyersHasMedicines.size(); i++){
            buyersHasMedicines.remove(i);
            i--;
        }
        return buyersHasMedicines;
    }

    @RequestMapping("/request11_type")
    List<BuyersHasMedicines> getListOfBuyersThatBuySelectedTypeOfMedicineMostOften(
            @RequestParam String nameOfType){
        List<BuyersHasMedicines> buyersHasMedicines =
                buyerHasMedicinesRepository.
                        getListOfBuyersThatBuySelectedTypeOfMedicineMostOften(nameOfType);
        for (int i = 1; i < buyersHasMedicines.size(); i++){
            buyersHasMedicines.remove(i);
            i--;
        }
        return buyersHasMedicines;
    }
}