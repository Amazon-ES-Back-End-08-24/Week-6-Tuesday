package org.ironhack.week6tuesday.repository;

import org.ironhack.week6tuesday.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    // JPA
    List<Sale> findByBookIdAndClientName(Integer idBook, String nameClient);
    // todo JOIN with JPA (_)

    // JPQL
    // named bind parameter -> parámetro de enlace con nombre
    @Query("SELECT s.clientName, s.saleDate FROM Sale s WHERE s.bookId = :bookId and s.saleId = :saleId")
    List<Object[]> findClientNameAndSaleDateByBookId(@Param("bookId") Integer bookId, @Param("saleId") Integer saleId);

//    @Query("SELECT s.clientName, s.saleDate FROM Sale s WHERE s.bookId = ?1 and s.saleId = ?2")
//    List<Object[]> findClientNameAndSaleDateByBookId(Integer bookId, Integer saleId);

    // positional bind parameter -> parámetro de enlace posicional
    @Query("SELECT Sale FROM Sale WHERE Sale.saleDate = ?2 AND Sale.bookId = ?1")
    List<Sale> findSalesByBookIdAndSaleDate(Integer bookId, String saleDate);

    @Query("SELECT s.clientName, s.saleDate, b.bookTitle FROM Sale s INNER JOIN Book b ON s.bookId = b.bookId " +
            "WHERE s.bookId = :bookId")
    List<Object[]> findClientNameAndSaleDateAndBookTitleByBookId(@Param("bookId") Integer bookId);
}
