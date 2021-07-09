package com.heaven.bookstore.repository;

import com.heaven.bookstore.domain.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddress,Long> {
}
