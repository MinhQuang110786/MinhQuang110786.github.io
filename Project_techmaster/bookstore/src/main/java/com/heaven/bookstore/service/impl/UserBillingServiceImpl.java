package com.heaven.bookstore.service.impl;

import com.heaven.bookstore.domain.UserBilling;
import com.heaven.bookstore.repository.UserBillingRepository;
import com.heaven.bookstore.service.UserBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBillingServiceImpl implements UserBillingService {
    @Autowired
    private UserBillingRepository userBillingRepository;


    @Override
    public UserBilling save(UserBilling userBilling) {
        return userBillingRepository.save(userBilling);
    }
}
