package com.heaven.bookstore.repository;

import com.heaven.bookstore.domain.UserBilling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBillingRepository extends JpaRepository<UserBilling,Long> {
}
