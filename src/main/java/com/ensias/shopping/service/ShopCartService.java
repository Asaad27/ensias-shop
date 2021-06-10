package com.ensias.shopping.service;

import com.ensias.shopping.entity.OrderItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ShopCartService {

    String NAME_PREFIX = "shop_cart_";


    void addCart(int productId, HttpServletRequest request) throws Exception;


    void remove(int productId, HttpServletRequest request) throws Exception;


    List<OrderItem> listCart(HttpServletRequest request) throws Exception;
}
