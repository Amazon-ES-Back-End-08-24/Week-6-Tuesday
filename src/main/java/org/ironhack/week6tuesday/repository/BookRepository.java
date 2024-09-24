package org.ironhack.week6tuesday.repository;

import org.ironhack.week6tuesday.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // JPA QUERIES
    List<Book> findAllByBookTitleLike(String title);
    List<Book> findAllByBookPriceGreaterThanEqual(Integer price);
    List<Book> findAllByBookPriceBetween(Integer price1, Integer price2);
    List<Book> findAllByBookTitleEndsWith(String endsWith);


    // JPQL (JPA - SQL)

    // MYSQL
}
