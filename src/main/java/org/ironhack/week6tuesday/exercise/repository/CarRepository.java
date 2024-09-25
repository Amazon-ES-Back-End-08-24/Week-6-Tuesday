package org.ironhack.week6tuesday.exercise.repository;

import org.ironhack.week6tuesday.exercise.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Encontrar todos los vehículos disponibles
    List<Car> findAllByStockGreaterThan(Integer stock);

    // Buscar vehículos por brand
    List<Car> findByBrand(String brand);

    // Buscar vehículos cuyo precio esté en un rango especificado
    List<Car> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Ordenar vehículos por precio de mayor a menor
    List<Car> findAllByOrderByPriceDesc();
}
