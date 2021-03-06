package com.heaven.bookstore.domain.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    private int roleId;
    private String name;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

}
