package com.heaven.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminportal")
public class AdminController {
   @GetMapping
    public String index(){
       return "redirect:/adminportal/home";
   }

   @GetMapping("/home")
   public String home(){
       return "home";
   }

}
