package com.heaven.bookstore.controller;

import com.heaven.bookstore.domain.Book;
import com.heaven.bookstore.domain.CartItem;
import com.heaven.bookstore.domain.ShoppingCart;
import com.heaven.bookstore.domain.User;
import com.heaven.bookstore.service.BookService;
import com.heaven.bookstore.service.CartItemService;
import com.heaven.bookstore.service.ShoppingCartService;
import com.heaven.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private BookService bookService;
    @GetMapping("/cart")
    public String shoppingCart(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);
        model.addAttribute("cartItemList",cartItemList);
        model.addAttribute("shoppingCart",shoppingCart);
        return "shoppingCart";

    }

    @RequestMapping("/addItem")
    public String addItem(
            @ModelAttribute("book") Book book,
            @ModelAttribute("qty") String qty,
            Principal principal, RedirectAttributes redirectAttributes
    ) {
        User user = userService.findByUsername(principal.getName());
        book = bookService.findOne(book.getId());

        if (Integer.parseInt(qty) > book.getInStockNumber()) {
            redirectAttributes.addFlashAttribute("notEnoughStock", true);
            return "redirect:/bookDetail?id="+book.getId();
        }

        CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
        redirectAttributes.addFlashAttribute("addBookSuccess", true);

        return "redirect:/bookDetail?id="+book.getId();
    }

    @PostMapping("/updateCartItem")
    public String updateShoppingCart(
            @ModelAttribute("id") Long cardItemId,
            @ModelAttribute("qty") int qty
    ){
        CartItem cartItem = cartItemService.findById(cardItemId);
        cartItem.setQty(qty);
        cartItemService.updateCartItem(cartItem);
        return "redirect:/shoppingCart/cart";
    }

    @GetMapping("/removeItem")
    public String removeItem(@RequestParam("id") Long id){
        cartItemService.removeCartItem(cartItemService.findById(id));
        return "redirect:/shoppingCart/cart";
    }

}
