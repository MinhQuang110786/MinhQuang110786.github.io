package com.heaven.bookstore.service;

import com.heaven.bookstore.domain.*;


public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
                      BillingAddress billingAddress, Payment payment, String shippingMethod, User user);
    Order findOne(Long id);
}
