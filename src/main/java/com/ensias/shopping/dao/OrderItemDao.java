package com.ensias.shopping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ensias.shopping.entity.OrderItem;

import java.util.List;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrderId(int orderId);
}
