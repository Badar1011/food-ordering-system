package com.uppercrust.foodorderingsystem.Authentication.User;

import com.uppercrust.foodorderingsystem.Authentication.Role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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

    @Email(message = "incorrect email address given", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Column(unique = true)
    private String email;


    private String password;

    private int number;

    private boolean enabled;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
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

 /*   public void setRoles(Role... roles){
        this.roles.addAll( Arrays.asList(roles));
    }*/
}