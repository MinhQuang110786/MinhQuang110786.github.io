package com.heaven.bookstore.service.impl;

import com.heaven.bookstore.domain.Payment;
import com.heaven.bookstore.domain.UserPayment;
import com.heaven.bookstore.repository.PaymentRepository;
import com.heaven.bookstore.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payment setByUserPayment(UserPayment userPayment, Payment payment) {
        payment.setType(userPayment.getType());
        payment.setCardName(userPayment.getCardName());
        payment.setCardNumber(userPayment.getCardNumber());
        payment.setExpireMonth(userPayment.getExpireMonth());
        payment.setExpireYear(userPayment.getExpireYear());
        payment.setCvc(userPayment.getCvc());
        payment.setHolderName(userPayment.getHolderName());
//        paymentRepository.save(payment);
        return payment;
    }
}
