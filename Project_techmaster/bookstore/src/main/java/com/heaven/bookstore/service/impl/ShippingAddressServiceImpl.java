package com.heaven.bookstore.service.impl;

import com.heaven.bookstore.domain.ShippingAddress;
import com.heaven.bookstore.domain.UserShipping;
import com.heaven.bookstore.repository.ShippingAddressRepository;
import com.heaven.bookstore.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepo;
    @Override
    public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {
        shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
        shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
        shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
        shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
        shippingAddress.setShippingAddressStreet1(userShipping.getUserShippingStreet1());
        shippingAddress.setShippingAddressStreet2(userShipping.getUserShippingStreet2());
        shippingAddress.setShippingAddressZipcode(userShipping.getUserShippingZipcode());
//        shippingAddressRepo.save(shippingAddress);

        return shippingAddress;
    }
}
