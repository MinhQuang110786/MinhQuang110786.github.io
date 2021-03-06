package com.heaven.bookstore.repository;

import com.heaven.bookstore.domain.User;
import com.heaven.bookstore.domain.security.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
    Stream<PasswordResetToken> findAllByExpireDateLessThan(Date now);
    @Modifying
    @Query("delete from PasswordResetToken t where t.expireDate<=?1")
    void deleteAllExpiredSince(Date now);
}
