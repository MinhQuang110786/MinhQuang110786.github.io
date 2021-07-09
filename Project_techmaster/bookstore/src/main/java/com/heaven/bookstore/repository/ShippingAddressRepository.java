package com.heaven.bookstore.repository;

import com.heaven.bookstore.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress,Long> {
}
