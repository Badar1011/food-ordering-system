package com.uppercrust.foodorderingsystem.Authentication.User;

import com.uppercrust.foodorderingsystem.Authentication.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

   User findByEmail(String email);
}
