package com.uppercrust.foodorderingsystem;

import com.uppercrust.foodorderingsystem.Authentication.Role.Role;
import com.uppercrust.foodorderingsystem.Authentication.Role.RoleRepository;
import com.uppercrust.foodorderingsystem.Authentication.User.User;
import com.uppercrust.foodorderingsystem.Authentication.User.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}


@Slf4j
@Transactional
@Component
class Runner implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // log.info("hello bee");
        //  Role roleUser = this.roleRepository.save(new Role("ROLE_USER"));
        //   log.info("role user" + roleUser);
        //   Role roleAdmin = this.roleRepository.save(new Role("ROLE_ADMIN"));
        //   User user = new User("badar@gmail.com", "123", 123123, true, roleUser);
     /*   User user1 = this.userRepository.save(user);
        log.info("user info " + user1);*/
    }
}