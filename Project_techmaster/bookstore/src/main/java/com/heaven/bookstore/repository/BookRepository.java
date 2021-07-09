package com.heaven.bookstore.repository;
import com.heaven.bookstore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findBookByCategory(String category);

    List<Book> findBookByTitleContains(String keyword);
}
