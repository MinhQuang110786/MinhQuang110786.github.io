package com.heaven.bookstore.service;

import com.heaven.bookstore.domain.Payment;
import com.heaven.bookstore.domain.UserPayment;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment,Payment payment);
}
