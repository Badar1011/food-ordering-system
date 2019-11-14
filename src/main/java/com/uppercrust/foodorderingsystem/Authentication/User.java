package com.uppercrust.foodorderingsystem.Authentication;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "roles")
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Email
    private String email;

    private String password;

    private int number;

    private boolean enabled;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles;

    public User(@Email String email, String password, int number, boolean enabled, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.number = number;
        this.enabled = enabled;
        this.roles.addAll(roles);
    }

    public User(@Email String email, String password, int number, boolean enabled, Role... roles) {
        this(email, password, number, enabled, new HashSet<>(Arrays.asList(roles)));
    }


}