package org.ironhack.week6tuesday.exercise.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "car_sales")
public class CarSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long carId;

    private Integer quantity;
    private LocalDate salesDate;


    public CarSale(Long id, Long carId, Integer quantity, LocalDate salesDate) {
        this.id = id;
        this.carId = carId;
        this.quantity = quantity;
        this.salesDate = salesDate;
    }

    public CarSale() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(LocalDate salesDate) {
        this.salesDate = salesDate;
    }
}
