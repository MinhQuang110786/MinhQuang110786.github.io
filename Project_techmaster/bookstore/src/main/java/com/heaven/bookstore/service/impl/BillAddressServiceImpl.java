package com.heaven.bookstore.service.impl;

import com.heaven.bookstore.domain.BillingAddress;
import com.heaven.bookstore.domain.UserBilling;
import com.heaven.bookstore.repository.BillingAddressRepository;
import com.heaven.bookstore.service.BillingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillAddressServiceImpl implements BillingAddressService {
    @Autowired
    private BillingAddressRepository billingAddressRepository;

    @Override
    public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) {
        billingAddress.setBillingAddressName(userBilling.getUserBillingName());
        billingAddress.setBillingAddressStreet1(userBilling.getUserBillingStreet1());
        billingAddress.setBillingAddressStreet2(userBilling.getUserBillingStreet2());
        billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
        billingAddress.setBillingAddressState(userBilling.getUserBillingState());
        billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());
        billingAddress.setBillingAddressZipcode(userBilling.getUserBillingZipcode());

//        billingAddressRepository.save(billingAddress);
        return billingAddress;
    }
}
