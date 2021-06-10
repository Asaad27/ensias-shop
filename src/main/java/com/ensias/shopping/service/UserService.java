package com.ensias.shopping.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ensias.shopping.entity.User;

import java.util.List;

public interface UserService {

    User findById(int id);


    Page<User> findAll(Pageable pageable);


    List<User> findAllExample(Example<User> example);

    void update(User user);


    int create(User user);

    void delById(int id);


    List<User> findByUsername(String username);


    User checkLogin(String username,String password);


}
