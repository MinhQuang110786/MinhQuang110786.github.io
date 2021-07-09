package com.heaven.bookstore.controller;

import com.heaven.bookstore.domain.Book;
import com.heaven.bookstore.domain.User;
import com.heaven.bookstore.service.BookService;
import com.heaven.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @GetMapping("/searchByCategory")
    public String searchByCategory(
            @RequestParam("category")String category,
            Model model, Principal principal
    ){
        if(principal!=null){
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        String classActiveCategory = "active"+category;
        classActiveCategory = classActiveCategory.replaceAll("\\s+","");
        classActiveCategory = classActiveCategory.replaceAll("\\&","");
        model.addAttribute(classActiveCategory,true);
        List<Book> bookList = bookService.findByCategory(category);
        if(bookList.isEmpty()){
            model.addAttribute("emptyList",true);
            return "bookshelf";
        }
        model.addAttribute("bookList",bookList);

        return "bookshelf";
    }
    @PostMapping("/searchBook")
    public String search(
            @ModelAttribute("keyword")String keyword,
            Principal principal,Model model
    ){
        if(principal!=null){
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        List<Book> bookList = bookService.blurySearch(keyword);
        if(bookList.isEmpty()){
            model.addAttribute("emptyList",true);
            return "bookshelf";
        }
        model.addAttribute("bookList",bookList);
        return "bookshelf";
    }
}
