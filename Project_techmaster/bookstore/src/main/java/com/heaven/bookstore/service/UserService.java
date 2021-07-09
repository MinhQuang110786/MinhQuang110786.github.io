package com.heaven.bookstore.service;

import com.heaven.bookstore.domain.User;
import com.heaven.bookstore.domain.UserBilling;
import com.heaven.bookstore.domain.UserPayment;
import com.heaven.bookstore.domain.UserShipping;
import com.heaven.bookstore.domain.security.PasswordResetToken;
import com.heaven.bookstore.domain.security.UserRole;

import java.util.Set;

public interface UserService {
    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);
    User findByUsername(String username);
    User findByEmail(String email);

    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User save(User user);
    void updateUserBilling(UserBilling userBilling, UserPayment userPayment,User user);

    void setUserDefaultPayment(Long userPaymentId,User user);

    void updateUserShipping(UserShipping userShipping, User user);

    void setUserDefaultShipping(Long userAddressId, User user);

    User findById(Long id);
}
