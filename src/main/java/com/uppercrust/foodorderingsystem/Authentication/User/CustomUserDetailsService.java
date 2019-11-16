package com.uppercrust.foodorderingsystem.Authentication.User;

import com.uppercrust.foodorderingsystem.Authentication.Role.Role;
import com.uppercrust.foodorderingsystem.Authentication.Role.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null)
            return new CustomUserDetails(user);
        else
            throw new UsernameNotFoundException("could not find: " + email);
    }


    public User save(User user) {
        // encode the password here
        // give user the necessary role
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("user in save"+ user);


        Role role = this.roleRepository.findByRole("ROLE_USER");
        Role roleAdmin = this.roleRepository.findByRole("ROLE_ADMIN");
        log.info("role info >>>"+ role);
        List<Role> listOfRole = Arrays.asList(role, roleAdmin);
        log.info("list of role info >>>"+ listOfRole);
        user.setRoles(listOfRole);
        log.info("User created: " + user);
        return userRepository.save(user);
    }


    // UPDATE PASSWORD
    @Override
    public UserDetails updatePassword(UserDetails userDetails, String s) {
        return null;
    }
}