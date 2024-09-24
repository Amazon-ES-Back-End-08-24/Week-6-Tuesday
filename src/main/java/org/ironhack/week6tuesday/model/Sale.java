package org.ironhack.week6tuesday.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleId;

    private String clientName;
    private String saleDate;
    private Integer bookId;

    public Sale(Integer saleId, String clientName, String saleDate, Integer bookId) {
        this.saleId = saleId;
        this.clientName = clientName;
        this.saleDate = saleDate;
        this.bookId = bookId;
    }

    public Sale() {

    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
