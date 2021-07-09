package com.heaven.bookstore.service;

import com.heaven.bookstore.domain.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
    void clearShoppingCart(ShoppingCart shoppingCart);
}
