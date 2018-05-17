package com.example.demo.repository;

import com.example.demo.model.BuyersHasMedicines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BuyerHasMedicinesRepository extends JpaRepository<BuyersHasMedicines, Integer> {
    @Query("select buyerHasMedicines from BuyersHasMedicines buyerHasMedicines where buyerHasMedicines.datesOfOrderingAndReceiving.orderStatus.id = 3")
    List<BuyersHasMedicines> getBuyerThatDidntComeForMedicine();

    @Query("select count (buyerHasMedicines) from BuyersHasMedicines buyerHasMedicines " +
            "where buyerHasMedicines.datesOfOrderingAndReceiving.orderStatus.id = 3")
    Integer getAmountOfBuyerThatDidntComeForMedicine();

    @Query("select buyerHasMedicines from BuyersHasMedicines buyerHasMedicines" +
            " where buyerHasMedicines.datesOfOrderingAndReceiving.orderStatus.id = 2")
    List<BuyersHasMedicines> getAllBuyersThatWaitingForMedicines();

    @Query("select count (buyerHasMedicines) from BuyersHasMedicines buyerHasMedicines" +
            " where buyerHasMedicines.datesOfOrderingAndReceiving.orderStatus.id = 2")
    Integer getAmountOfAllBuyersThatWaitingForMedicines();

    @Query("select buyerHasMedicines from BuyersHasMedicines buyerHasMedicines" +
            " where buyerHasMedicines.datesOfOrderingAndReceiving.orderStatus.id = 2 and " +
            "buyerHasMedicines.medicine.typeOfMedicine.nameOfType = :nameOfType")
    List<BuyersHasMedicines> getBuyersThatWaitingForMedicinesOfSelectedType(
            @Param("nameOfType")String nameOfType);

    @Query("select count (buyerHasMedicines) from BuyersHasMedicines buyerHasMedicines" +
            " where buyerHasMedicines.datesOfOrderingAndReceiving.orderStatus.id = 2 and " +
            "buyerHasMedicines.medicine.typeOfMedicine.nameOfType = :nameOfType")
    Integer getAmountOfBuyersThatWaitingForMedicinesOfSelectedType(
            @Param("nameOfType")String nameOfType);

    @Query("select buyerHasMedicines, count(buyerHasMedicines.buyer.id) from BuyersHasMedicines buyerHasMedicines " +
            "group by buyerHasMedicines.medicine.id order by count (buyerHasMedicines.buyer.id) desc")
    List<BuyersHasMedicines> getAllTheMostPopularMedicines();

    @Query("select buyerHasMedicines, count(buyerHasMedicines.buyer.id) from BuyersHasMedicines buyerHasMedicines " +
            "where buyerHasMedicines.medicine.typeOfMedicine.nameOfType = :nameOfType " +
            "group by buyerHasMedicines.medicine.id order by count (buyerHasMedicines.buyer.id) desc")
    List<BuyersHasMedicines> getTheMostPopularMedicinesOfSelectedType(
            @Param("nameOfType")String nameOfType
    );

    @Query("select count (buyerHasMedicines.buyer.id), buyerHasMedicines.medicine.nameOfMedicine " +
            "from BuyersHasMedicines buyerHasMedicines " +
            "where buyerHasMedicines.medicine.nameOfMedicine = :nameOfMedicine and " +
            "buyerHasMedicines.datesOfOrderingAndReceiving.dateOfOrdering " +
            "between :firstDate and :secondDate")
    Integer getAmountOfBuyersThatOrderSelectedMedicineBetweenSelectedDates(
        @Param("nameOfMedicine")String nameOfMedicine, @Param("firstDate")Date firstDate,
        @Param("secondDate")Date secondDate
    );

    @Query("select buyerHasMedicines.buyer.PIB, buyerHasMedicines.medicine.nameOfMedicine " +
            "from BuyersHasMedicines buyerHasMedicines " +
            "where buyerHasMedicines.medicine.nameOfMedicine = :nameOfMedicine and " +
            "buyerHasMedicines.datesOfOrderingAndReceiving.dateOfOrdering " +
            "between :firstDate and :secondDate")
    List<BuyersHasMedicines> getListOfBuyersThatOrderSelectedMedicineBetweenSelectedDates(
            @Param("nameOfMedicine")String nameOfMedicine, @Param("firstDate")Date firstDate,
            @Param("secondDate")Date secondDate
    );

    @Query("select count (buyerHasMedicines.buyer.id) " +
            "from BuyersHasMedicines buyerHasMedicines " +
            "where buyerHasMedicines.medicine.typeOfMedicine.nameOfType = :nameOfType and " +
            "buyerHasMedicines.datesOfOrderingAndReceiving.dateOfOrdering " +
            "between :firstDate and :secondDate")
    Integer getAmountOfBuyersThatOrderSelectedTypeOfMedicineBetweenSelectedDates(
            @Param("nameOfType")String nameOfType, @Param("firstDate")Date firstDate,
            @Param("secondDate")Date secondDate
    );

    @Query("select buyerHasMedicines.buyer.PIB, buyerHasMedicines.medicine.nameOfMedicine " +
            "from BuyersHasMedicines buyerHasMedicines " +
            "where buyerHasMedicines.medicine.typeOfMedicine.nameOfType = :nameOfType and " +
            "buyerHasMedicines.datesOfOrderingAndReceiving.dateOfOrdering " +
            "between :firstDate and :secondDate")
    List<BuyersHasMedicines> getListOfBuyersThatOrderSelectedTypeOfMedicineBetweenSelectedDates(
            @Param("nameOfType")String nameOfType, @Param("firstDate")Date firstDate,
            @Param("secondDate")Date secondDate
    );

    @Query("select buyerHasMedicines.buyer.PIB, buyerHasMedicines.medicine.nameOfMedicine" +
            " from BuyersHasMedicines buyerHasMedicines " +
            "where buyerHasMedicines.datesOfOrderingAndReceiving.orderStatus.id = 4")
    List<BuyersHasMedicines> getListOfOrdersThatAreMakingNow();

    @Query("select count (buyerHasMedicines.medicine.nameOfMedicine)" +
            " from BuyersHasMedicines buyerHasMedicines " +
            "where buyerHasMedicines.datesOfOrderingAndReceiving.orderStatus.id = 4")
    Integer getAmountOfOrdersThatAreMakingNow();

    @Query("select buyerHasMedicines2.buyer.PIB, count(buyerHasMedicines2.medicine.id) " +
            "from BuyersHasMedicines buyerHasMedicines2 " +
            "group by buyerHasMedicines2.buyer.id order by count(buyerHasMedicines2.medicine.id) desc")
    List<BuyersHasMedicines> getListOfBuyersThatBuyMostOften();

    @Query("select buyerHasMedicines2.buyer.PIB, count(buyerHasMedicines2.medicine.id) " +
            "from BuyersHasMedicines buyerHasMedicines2 " +
            "where buyerHasMedicines2.medicine.nameOfMedicine = :nameOfMedicine " +
            "group by buyerHasMedicines2.buyer.id " +
            "order by count(buyerHasMedicines2.medicine.id) desc")
    List<BuyersHasMedicines> getListOfBuyersThatBuySelectedMedicineMostOften(
            @Param("nameOfMedicine")String nameOfMedicine);

    @Query("select buyerHasMedicines2.buyer.PIB, count(buyerHasMedicines2.medicine.id) " +
            "from BuyersHasMedicines buyerHasMedicines2 " +
            "where buyerHasMedicines2.medicine.typeOfMedicine.nameOfType = :nameOfType " +
            "group by buyerHasMedicines2.buyer.id " +
            "order by count(buyerHasMedicines2.medicine.id) desc")
    List<BuyersHasMedicines> getListOfBuyersThatBuySelectedTypeOfMedicineMostOften(
            @Param("nameOfType")String nameOfType);
}