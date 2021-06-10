package com.ensias.shopping.service.impl;

import com.ensias.shopping.dao.OrderDao;
import com.ensias.shopping.dao.OrderItemDao;
import com.ensias.shopping.dao.ProductDao;
import com.ensias.shopping.service.OrderService;
import com.ensias.shopping.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ensias.shopping.entity.Order;
import com.ensias.shopping.entity.OrderItem;
import com.ensias.shopping.entity.Product;
import com.ensias.shopping.entity.User;
import com.ensias.shopping.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ShopCartService shopCartService;


    @Override
    public Order findById(int id) {
        return orderDao.getOne(id);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderDao.findAll(pageable);
    }

    @Override
    public List<Order> findAllExample(Example<Order> example) {
        return orderDao.findAll(example);
    }

    @Override
    public void update(Order order) {
        orderDao.save(order);
    }

    @Override
    public int create(Order order) {
        Order order1 = orderDao.save(order);
        return order1.getId();
    }

    @Override
    public void delById(int id) {
        orderDao.delete(id);
    }


    @Override
    public List<OrderItem> findItems(int orderId) {
        List<OrderItem> list = orderItemDao.findByOrderId(orderId);
        for (OrderItem orderItem : list) {
            Product product = productDao.findOne(orderItem.getProductId());
            orderItem.setProduct(product);
        }
        return list;
    }


    @Override
    public void updateStatus(int id, int status) {
        Order order = orderDao.findOne(id);
        order.setState(status);
        orderDao.save(order);
    }


    @Override
    public List<Order> findUserOrder(HttpServletRequest request) {
        //Récupérez l'identifiant de l'utilisateur connecté à partir de la session et retrouvez sa commande
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("connectez vous！");
        User loginUser = (User) user;
        List<Order> orders = orderDao.findByUserId(loginUser.getId());
        return orders;
    }


    @Override
    public void pay(int orderId) {

        Order order = orderDao.findOne(orderId);
        if (order == null)
            throw new RuntimeException("La commande n'existe pas");
        orderDao.updateState(STATE_WAITE_SEND,order.getId());
    }


    @Override
    @Transactional
    public void submit(String name, String phone, String addr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("connectez vous");
        User loginUser = (User) user;
        Order order = new Order();
        order.setName(name);
        order.setPhone(phone);
        order.setAddr(addr);
        order.setOrderTime(new Date());
        order.setUserId(loginUser.getId());
        order.setState(STATE_NO_PAY);
        List<OrderItem> orderItems = shopCartService.listCart(request);
        Double total = 0.0;
        order.setTotal(total);
        order = orderDao.save(order);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getId());
            total += orderItem.getSubTotal();
            orderItemDao.save(orderItem);
        }
        order.setTotal(total);
        orderDao.save(order);
        response.sendRedirect("/mall/order/toList.html");
    }


    @Override
    public void receive(int orderId) {
        Order order = orderDao.findOne(orderId);
        if (order == null)
            throw new RuntimeException("La commande n'existe pas");
        orderDao.updateState(STATE_COMPLETE,order.getId());
    }
}
