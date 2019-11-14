package com.uppercrust.foodorderingsystem;

import com.uppercrust.foodorderingsystem.Authentication.Role;
import com.uppercrust.foodorderingsystem.Authentication.RoleRepository;
import com.uppercrust.foodorderingsystem.Authentication.User;
import com.uppercrust.foodorderingsystem.Authentication.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}


@Slf4j
@Component
class Runner implements ApplicationRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role roleUser = this.roleRepository.save(new Role("ROLE_USER"));
        Role roleAdmin = this.roleRepository.save(new Role("ROLE_ADMIN"));
        User user = new User("badar@gmail.com", "123", 123123, true, roleAdmin, roleUser);
        user = userRepository.save(user);
        log.info("user info " + user);
    }
}
