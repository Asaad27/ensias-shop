package com.ensias.shopping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ensias.shopping.entity.AdminUser;

public interface AdminUserDao extends JpaRepository<AdminUser, Integer> {
    AdminUser findByUsernameAndPassword(String username, String pwd);
}
