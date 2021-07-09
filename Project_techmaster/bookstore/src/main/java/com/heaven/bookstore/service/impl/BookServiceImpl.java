package com.heaven.bookstore.service.impl;

import com.heaven.bookstore.domain.Book;
import com.heaven.bookstore.repository.BookRepository;
import com.heaven.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        List<Book> bookList = bookRepository.findAll();
        List<Book> activeBookList = new ArrayList<>();
        for(Book book:bookList){
            if(book.isActive()){
                activeBookList.add(book);
            }
        }
        return activeBookList;
    }

    @Override
    public Book findOne(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findByCategory(String category) {

        List<Book> bookList =  bookRepository.findBookByCategory(category);
        List<Book> activeBookList = new ArrayList<>();
        for(Book book:bookList){
            if(book.isActive()){
                activeBookList.add(book);
            }
        }
        return activeBookList;
    }

    @Override
    public List<Book> blurySearch(String keyword) {
        List<Book> bookList =  bookRepository.findBookByTitleContains(keyword);
        List<Book> activeBookList = new ArrayList<>();
        for(Book book:bookList){
            if(book.isActive()){
                activeBookList.add(book);
            }
        }
        return activeBookList;
    }

    @Override
    public void removeOne(Long id) {
        bookRepository.deleteById(id);
    }
}
