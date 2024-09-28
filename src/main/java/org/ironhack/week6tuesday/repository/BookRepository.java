package org.ironhack.week6tuesday.repository;

import org.ironhack.week6tuesday.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // JPA QUERIES
    List<Book> findAllByBookTitleLike(String title);

    List<Book> findAllByBookTitleEndsWith(String endsWith);

    List<Book> findByBookTitleContaining(String infix);

    List<Book> findByBookPrice(Integer price);

    List<Book> findAllByBookPriceGreaterThanEqual(Integer price);

    List<Book> findAllByBookPriceBetween(Integer price1, Integer price2);

    // JPQL (JPA - SQL)
    @Query("SELECT SUM(b.bookPrice) FROM Book b")
    Integer sumBookPrice();

    @Query("SELECT AVG(b.bookPrice) FROM Book b")
    Integer avgBookPrice();

    @Query("SELECT MAX(b.bookPrice) FROM Book b")
    Integer maxBookPrice();

    @Query("SELECT MIN(b.bookPrice) FROM Book b")
    Integer minBookPrice();

    @Query("SELECT b FROM Book b WHERE b.bookTitle LIKE %:title%")
    List<Book> findByBookTitleLike(@Param("title") String title);

    // SQL NATIVO
    @Query(value = "SELECT SUM(b.price) FROM books b", nativeQuery = true)
    Integer sumBookPriceNative();

    @Query(value = "SELECT AVG(b.price) FROM books b", nativeQuery = true)
    Integer avgBookPriceNative();

    @Query(value = "SELECT MAX(b.price) FROM books b", nativeQuery = true)
    Integer maxBookPriceNative();

    @Query(value = "SELECT MIN(b.price) FROM books b", nativeQuery = true)
    Integer minBookPriceNative();

    @Query(value = "SELECT * FROM books WHERE price > (SELECT AVG(price) FROM books)", nativeQuery = true)
    List<Book> findBooksAboveAveragePrice();
}
