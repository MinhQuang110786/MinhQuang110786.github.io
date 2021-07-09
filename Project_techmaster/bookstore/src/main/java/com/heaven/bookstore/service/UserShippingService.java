package com.heaven.bookstore.service;

import com.heaven.bookstore.domain.UserShipping;

public interface UserShippingService {
    UserShipping findById(Long id);
    void removeById(Long id);
    UserShipping save(UserShipping userShipping);

}
