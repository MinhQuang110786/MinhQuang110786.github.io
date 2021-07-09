package com.heaven.bookstore.service.impl;

import com.heaven.bookstore.domain.UserPayment;
import com.heaven.bookstore.repository.UserPaymentRepository;
import com.heaven.bookstore.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
    @Autowired
    private UserPaymentRepository userPaymentRepository;


    @Override
    public UserPayment findById(Long id) {
        return userPaymentRepository.findById(id).orElse(null);
    }

    @Override
    public void removeById(Long id) {
        userPaymentRepository.deleteById(id);
    }

    @Override
    public UserPayment save(UserPayment userPayment) {
        return userPaymentRepository.save(userPayment);
    }
}
