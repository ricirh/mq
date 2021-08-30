package com.server;


import com.entity.Goods;
import com.entity.Order;
import com.entity.User;

public interface OrderServer {
    int getRemain(Goods good);

    boolean createOrder(User user, Goods good);

    void cancelOrder(User user, Order order);
}
