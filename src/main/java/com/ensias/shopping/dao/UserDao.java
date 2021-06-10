package com.ensias.shopping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ensias.shopping.entity.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);


    List<User> findByUsername(String username);
}
