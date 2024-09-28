package org.ironhack.week6tuesday.exercise.repository;

import org.ironhack.week6tuesday.exercise.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    // Buscar vehículos por brand y ordenarlos por price de menor a mayor:
    @Query("SELECT c FROM Car c WHERE c.brand = :brand ORDER BY c.price ASC")
    List<Car> findCarsByBrandOrderByPriceAsc(@Param("brand") String brand);

    // Buscar vehículos cuyo precio esté dentro de un rango:
    @Query("SELECT c FROM Car c WHERE c.price BETWEEN :minPrice AND :maxPrice")
    List<Car> findCarsByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    //Encontrar vehículos que contengan un término en su model:
    @Query("SELECT c FROM Car c WHERE c.model LIKE %:term%")
    List<Car> findCarsByModelContaining(@Param("term") String term);

    //Encontrar el vehículo más caro:
    @Query("SELECT c FROM Car c WHERE c.price = (SELECT MAX(c2.price) FROM Car c2)")
    Car findMostExpensiveCar();

    // Encontrar todos los vehículos con un precio mayor a un valor específico:
    @Query(value = "SELECT * FROM cars WHERE price > :price", nativeQuery = true)
    List<Car> findCarsWithPriceGreaterThan(@Param("price") Double price);

    // Buscar vehículos por brand utilizando una consulta SQL nativa:
    @Query(value = "SELECT * FROM cars WHERE brand = :brand", nativeQuery = true)
    List<Car> findCarsByBrandNative(@Param("brand") String brand);

    // Buscar vehículos cuyo model comience con una letra específica:
    // Concat se usa para concatenar el prefijo y añadirle la wildcard "%" luego,
    // porque si no en la query nativa puesto como ":prefix%" no lo interpreta bien
    @Query(value = "SELECT * FROM cars WHERE model LIKE CONCAT(:prefix, '%')", nativeQuery = true)
    List<Car> findCarsByModelStartingWith(@Param("prefix") String prefix);

    // Encontrar el vehículo más barato usando SQL nativo:
    @Query(value = "SELECT * FROM cars WHERE price = (SELECT MIN(price) FROM cars)", nativeQuery = true)
    Car findCheapestCarNative();
}
