package com.heaven.bookstore.service;

import com.heaven.bookstore.domain.BillingAddress;
import com.heaven.bookstore.domain.UserBilling;

public interface BillingAddressService {
    BillingAddress setByUserBilling(UserBilling userBilling,BillingAddress billingAddress);
}
