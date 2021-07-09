package com.heaven.bookstore.repository;

import com.heaven.bookstore.domain.CartItem;
import com.heaven.bookstore.domain.Order;
import com.heaven.bookstore.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    List<CartItem> findByOrder(Order order);
}
