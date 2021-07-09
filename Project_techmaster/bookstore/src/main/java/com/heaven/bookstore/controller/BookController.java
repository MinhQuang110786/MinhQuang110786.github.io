package com.heaven.bookstore.controller;


import com.heaven.bookstore.domain.Book;
import com.heaven.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("adminportal/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addBook(Model model){
        Book book = new Book();
        model.addAttribute("book",book);
        return "addBook";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addBookPost(
            @ModelAttribute("book") Book book,
            HttpServletRequest request
    ){
        bookService.save(book);
        MultipartFile bookImage = book.getBookImage();
        try{
            byte[] bytes = bookImage.getBytes();
            String name = book.getId() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/book" + name)));
            stream.write(bytes);
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/adminportal/book/bookList";
    }

    @GetMapping("/bookList")
    public String bookList(Model model){
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList",bookList);
        return "bookList";
    }

    @GetMapping("/bookInfo")
    public String bookInfo(@RequestParam("id") Long id, Model model){
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);

        return "bookInfo";
    }

    @GetMapping("/updateBook")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateBook(@RequestParam("id") Long id, Model model){
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "updateBook";
    }

    @PostMapping("/updateBook")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request){
        bookService.save(book);
        MultipartFile bookImage = book.getBookImage();
        if(!bookImage.isEmpty()){
            try {
                byte[] bytes = bookImage.getBytes();
                String name = book.getId() + ".png";
                Path path = Paths.get("src/main/resources/static/images/book/" + name);
                if(Files.exists(path))
                    Files.delete(Paths.get("src/main/resources/static/images/book/" + name));
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/book/" + name)));
                stream.write(bytes);
                stream.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        return "redirect:/adminportal/book/bookInfo?id=" + book.getId();
    }

    @PostMapping("/remove")
    @PreAuthorize("hasRole('ADMIN')")
    public String remove(
            @ModelAttribute("id") String id,Model model
    ){
        bookService.removeOne(Long.parseLong(id.substring(8)));
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList",bookList);
        return "redirect:/adminportal/book/bookList";
    }
}
