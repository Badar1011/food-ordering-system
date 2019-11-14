package com.uppercrust.foodorderingsystem.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class CustomUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return new CustomUserDetails(user);
        } else
            throw new UsernameNotFoundException("could not find: " + username);

    }


    public User save(User user) {
        // encode the password here

        return userRepository.save(user);
    }


    // UPDATE PASSWORD
    @Override
    public UserDetails updatePassword(UserDetails userDetails, String s) {
        return null;
    }
}