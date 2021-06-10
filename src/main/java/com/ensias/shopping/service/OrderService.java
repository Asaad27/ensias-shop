package com.ensias.shopping.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ensias.shopping.entity.Order;
import com.ensias.shopping.entity.OrderItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OrderService {


    int STATE_NO_PAY = 1;
    int STATE_WAITE_SEND = 2;
    int STATE_WAITE_RECEIVE = 3;
    int STATE_COMPLETE = 4;


    Order findById(int id);


    Page<Order> findAll(Pageable pageable);


    List<Order> findAllExample(Example<Order> example);


    void update(Order order);


    int create(Order order);


    void delById(int id);


    List<OrderItem> findItems(int orderId);


    void updateStatus(int id, int status);


    List<Order> findUserOrder(HttpServletRequest request);


    void pay(int orderId);


    void submit(String name, String phone, String addr, HttpServletRequest request, HttpServletResponse response) throws Exception;


    void receive(int orderId);
}
