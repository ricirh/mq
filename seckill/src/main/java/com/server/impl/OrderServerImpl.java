package com.server.impl;

import com.dao.OrderDao;
import com.entity.Goods;
import com.entity.Order;
import com.entity.User;
import com.server.OrderServer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderServerImpl implements OrderServer {
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public int getRemain(Goods good) {
        Jedis jedis = jedisPool.getResource();
        int num = Integer.parseInt(jedis.get(good.getName()));
        jedis.close();
        return num;

    }

    @Override
    public boolean createOrder(User user, Goods good) {
        Jedis jedis = jedisPool.getResource();
        if(jedis.decr("goodKey") >= 0){
            Order order = new Order();
            amqpTemplate.convertAndSend("order",order);
            jedis.close();
            return true;
        }else{
            jedis.close();
            return false;
        }
    }

    @Override
    public void cancelOrder(User user, Order order) {

    }
}
