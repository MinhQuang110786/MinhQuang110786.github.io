package com.heaven.bookstore.service.impl;

import com.heaven.bookstore.domain.UserShipping;
import com.heaven.bookstore.repository.UserShippingRepository;
import com.heaven.bookstore.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShippingServiceImpl implements UserShippingService {
    @Autowired
    UserShippingRepository userShippingRepository;

    @Override
    public UserShipping findById(Long id) {
        return userShippingRepository.findById(id).orElse(null);
    }

    @Override
    public void removeById(Long id) {
        userShippingRepository.deleteById(id);
    }

    @Override
    public UserShipping save(UserShipping userShipping) {
        return userShippingRepository.save(userShipping);
    }
}
