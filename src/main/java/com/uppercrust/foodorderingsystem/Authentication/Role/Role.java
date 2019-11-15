package com.uppercrust.foodorderingsystem.Authentication.Role;

import com.uppercrust.foodorderingsystem.Authentication.User.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "users")
@NoArgsConstructor
public class Role {

    @Id @GeneratedValue
    private Long id;

    private String role;

    public Role(String role, Set<User> users) {
        this.role = role;
        this.users.addAll(users);
    }

    public Role(String role)
    {
        this.role = role;
    }


    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}