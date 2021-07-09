package com.heaven.bookstore;

import com.heaven.bookstore.domain.User;
import com.heaven.bookstore.domain.security.Role;
import com.heaven.bookstore.domain.security.UserRole;
import com.heaven.bookstore.service.UserService;
import com.heaven.bookstore.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user0 = new User();
        user0.setFirstName("Quang");
        user0.setLastName("Minh");
        user0.setUsername("admin");
        user0.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
        user0.setEmail("quangbookstore1@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role0= new Role();
        role0.setRoleId(1);
        role0.setName("ROLE_ADMIN");
        userRoles.add(new UserRole(user0, role0));

        userService.createUser(user0, userRoles);



//        User user1 = new User();
//        user1.setFirstName("John");
//        user1.setLastName("Adams");
//        user1.setUsername("John");
//        user1.setPassword(SecurityUtility.passwordEncoder().encode("john"));
//        user1.setEmail("quangbookstore1@gmail.com");
////        Set<UserRole> userRoles = new HashSet<>();
//        Role role1= new Role();
//        role1.setRoleId(2);
//        role1.setName("ROLE_USER");
//        userRoles.add(new UserRole(user1, role1));
//
//        userService.createUser(user1, userRoles);
    }

}
