package org.ironhack.week6tuesday.exercise.repository;


import org.ironhack.week6tuesday.exercise.model.CarSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarSaleRepository extends JpaRepository<CarSale, Long> {

    // Encontrar todas las ventas realizadas en una fecha específica
    List<CarSale> findBySalesDate(LocalDate salesDate);

    //Esto devolverá una lista de objetos donde: (Object[0]) es el carId
    // y (Object[1]) es el conteo de ventas asociadas a ese carId
    @Query("SELECT cs.carId, COUNT(cs) FROM CarSale cs GROUP BY cs.carId")
    List<Object[]> countTotalSalesByCarId();

    // Buscar todas las ventas realizadas después de una fecha específica:
    @Query("SELECT cs FROM CarSale cs WHERE cs.salesDate > :salesDate")
    List<CarSale> findSalesAfterDate(@Param("salesDate") LocalDate salesDate);

    // Agrupar ventas por carId y contar el número total de ventas por cada vehículo:
    @Query("SELECT cs.carId, COUNT(cs.id) FROM CarSale cs GROUP BY cs.carId")
    List<Object[]> findTotalSalesPerCar();

    // Buscar todas las ventas para un vehículo específico usando su carId:
    @Query("SELECT cs FROM CarSale cs WHERE cs.carId = :carId")
    List<CarSale> findSalesByCarId(@Param("carId") Long carId);

    // Sumar todas las ventas realizadas para un vehículo específico:
    @Query(value = "SELECT SUM(quantity) FROM car_sales WHERE car_id = :carId", nativeQuery = true)
    Integer findTotalQuantitySoldForCar(@Param("carId") Long carId);

    // Buscar todas las ventas realizadas en un rango de fechas:
    @Query(value = "SELECT * FROM car_sales WHERE sales_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<CarSale> findSalesBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Realizar un JOIN entre las tablas Car y Sale para obtener las ventas con el precio del vehículo:
    @Query(value = "SELECT s.id, s.quantity, s.sales_date, c.price FROM car_sales s JOIN cars c ON s.car_id = c.id WHERE s.car_id = :carId", nativeQuery = true)
    List<Object[]> findSalesWithCarPrice(@Param("carId") Long carId);

    // Encontrar el total de ingresos (suma de precios) de un vehículo vendido en un rango de fechas:
    @Query(value = "SELECT SUM(c.price * s.quantity) FROM car_sales s JOIN cars c ON s.car_id = c.id WHERE s.sales_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    Double findTotalRevenueInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}

