package com.uppercrust.foodorderingsystem.Authentication.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uppercrust.foodorderingsystem.Authentication.User.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
@NoArgsConstructor
public class Role {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String role;

    public Role(String role, Set<User> users) {
        this.role = role;
        this.users.addAll(users);
    }

    public Role(String role)
    {
        this.role = role;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}