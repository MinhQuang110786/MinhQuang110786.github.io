package com.heaven.bookstore.service;

import com.heaven.bookstore.domain.UserPayment;

public interface UserPaymentService {
    UserPayment findById(Long id);
    void removeById(Long id);
    UserPayment save(UserPayment userPayment);
}
