package com.uppercrust.foodorderingsystem.Authentication.User;


import com.uppercrust.foodorderingsystem.Authentication.User.CustomUserDetailsService;
import com.uppercrust.foodorderingsystem.Authentication.User.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
public class UserController {

    private CustomUserDetailsService userService;

    public UserController(CustomUserDetailsService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        log.info("user controller" + user);
        User newUser =  userService.save(user);
        log.info("new user" + newUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).body(newUser);
    }
}