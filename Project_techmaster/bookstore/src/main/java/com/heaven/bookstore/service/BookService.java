package com.heaven.bookstore.service;
import com.heaven.bookstore.domain.Book;

import java.util.List;


public interface BookService {
    Book save(Book book);

    List<Book> findAll();

    Book findOne(Long id);

    List<Book> findByCategory(String category);

    List<Book> blurySearch(String keyword);
    void removeOne(Long id);
}
