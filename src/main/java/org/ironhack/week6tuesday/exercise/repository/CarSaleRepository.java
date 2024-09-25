package org.ironhack.week6tuesday.exercise.repository;


import org.ironhack.week6tuesday.exercise.model.CarSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarSaleRepository extends JpaRepository<CarSale, Long> {

    // Encontrar todas las ventas realizadas en una fecha específica
    List<CarSale> findBySalesDate(LocalDate salesDate);

    //Esto devolverá una lista de objetos donde: (Object[0]) es el carId
    // y (Object[1]) es el conteo de ventas asociadas a ese carId
    @Query("SELECT s.carId, COUNT(s) FROM CarSale s GROUP BY s.carId")
    List<Object[]> countTotalSalesByCarId();
}

