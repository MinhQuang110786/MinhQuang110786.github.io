package com.heaven.bookstore.repository;

import com.heaven.bookstore.domain.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentRepository extends JpaRepository<UserPayment,Long> {
}
