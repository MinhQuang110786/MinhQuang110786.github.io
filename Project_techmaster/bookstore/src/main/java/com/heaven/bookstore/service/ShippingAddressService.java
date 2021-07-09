package com.heaven.bookstore.service;

import com.heaven.bookstore.domain.ShippingAddress;
import com.heaven.bookstore.domain.UserShipping;
public interface ShippingAddressService {

    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
