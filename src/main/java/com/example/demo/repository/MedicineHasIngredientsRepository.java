package com.example.demo.repository;

import com.example.demo.model.BuyersHasMedicines;
import com.example.demo.model.MedicineHasIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface MedicineHasIngredientsRepository extends JpaRepository<MedicineHasIngredients, Integer> {
    @Query("select m.ingredients.name, sum(m.amountOfIngredients*b.amountOfMedicine) " +
            "from MedicineHasIngredients m " +
            "join BuyersHasMedicines b ON m.medicine.id = b.medicine.id " +
            "where b.datesOfOrderingAndReceiving.dateOfOrdering " +
            "between :firstDate and :secondDate " +
            "group by m.ingredients.id")
    List<MedicineHasIngredients> getVolumeOfIngredientsBetweenSelectedDates(
            @Param("firstDate")Date firstDate, @Param("secondDate")Date secondDate
    );

    @Query("select count(m.ingredients.id) " +
            "from MedicineHasIngredients m " +
            "join BuyersHasMedicines b ON m.medicine.id = b.medicine.id " +
            "where b.datesOfOrderingAndReceiving.orderStatus.nameOfStatus = :nameOfStatus ")
    Integer getAmountOfIngredientsOfMedicinesThatAreMaking(
            @Param("nameOfStatus") String nameOfStatus);

    @Query("select m.ingredients.name " +
            "from MedicineHasIngredients m " +
            "join BuyersHasMedicines b ON m.medicine.id = b.medicine.id " +
            "where b.datesOfOrderingAndReceiving.orderStatus.nameOfStatus = :nameOfStatus ")
    List<MedicineHasIngredients> getListOfIngredientsOfMedicinesThatAreMaking(
            @Param("nameOfStatus") String nameOfStatus);

    @Query("select m.ingredients.name, m.amountOfIngredients, " +
            "((m.amountOfIngredients/100)*m.ingredients.price) " +
            "from MedicineHasIngredients m " +
            "where m.medicine.nameOfMedicine = :nameOfMedicine")
    List<MedicineHasIngredients> getListOfVolumeAndPriceOfIngredientsOfMedicinesThatMakeInDrugstore(
            @Param("nameOfMedicine") String nameOfMedicine);
}
