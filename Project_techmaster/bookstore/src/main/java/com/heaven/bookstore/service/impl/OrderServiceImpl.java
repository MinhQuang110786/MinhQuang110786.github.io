package com.heaven.bookstore.service.impl;

import com.heaven.bookstore.domain.*;
import com.heaven.bookstore.repository.OrderRepository;
import com.heaven.bookstore.service.CartItemService;
import com.heaven.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemService cartItemService;
    @Override
    public  synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
                             BillingAddress billingAddress, Payment payment,
                             String shippingMethod, User user) {
        Order order = new Order();
        order.setBillingAddress(billingAddress);
        order.setShippingAddress(shippingAddress);
        order.setOrderStatus("created");
        order.setPayment(payment);
        order.setShippingMethod(shippingMethod);
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        for(CartItem cartItem:cartItemList){
            Book book = cartItem.getBook();
            cartItem.setOrder(order);
            book.setInStockNumber(book.getInStockNumber()-cartItem.getQty());
        }
        order.setCartItemList(cartItemList);
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderTotal(shoppingCart.getGrandTotal());
        shippingAddress.setOrder(order);
        billingAddress.setOrder(order);
        payment.setOrder(order);
        order.setUser(user);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order findOne(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
