package com.heaven.bookstore.service.impl;

import com.heaven.bookstore.domain.*;
import com.heaven.bookstore.repository.BookToCartItemRepository;
import com.heaven.bookstore.repository.CartItemRepository;
import com.heaven.bookstore.service.BookService;
import com.heaven.bookstore.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookToCartItemRepository bookToCartItemRepository;

    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        BigDecimal bigDecimal = new BigDecimal(cartItem.getBook().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        cartItem.setSubtotal(bigDecimal);
        cartItemRepository.save(cartItem);
        return cartItem;
    }

    @Override
    public CartItem addBookToCartItem(Book book, User user, int qty) {
        List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
        for(CartItem cartItem:cartItemList){
            if(book.getId()==cartItem.getBook().getId()){
                cartItem.setQty(cartItem.getQty()+qty);
                cartItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
                cartItemRepository.save(cartItem);
                return cartItem;
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setBook(book);
        cartItem.setQty(qty);
        cartItem.setSubtotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(qty)));
        cartItemRepository.save(cartItem);
        BookToCartItem bookToCartItem = new BookToCartItem();
        bookToCartItem.setBook(book);
        bookToCartItem.setCartItem(cartItem);
        bookToCartItemRepository.save(bookToCartItem);
        return cartItem;
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        bookToCartItemRepository.deleteByCartItem(cartItem);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> findByOrder(Order order) {
        return cartItemRepository.findByOrder(order);
    }
}
