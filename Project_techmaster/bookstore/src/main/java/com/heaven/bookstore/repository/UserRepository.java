package com.heaven.bookstore.repository;

import com.heaven.bookstore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.username=:username")
    User findByUsername(@Param("username") String username);
    @Query("SELECT u FROM User u WHERE u.email=:email")
    User findByEmail(@Param("email") String email);
}
