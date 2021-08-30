package com.receiver;

import com.dao.OrderDao;
import com.server.OrderServer;
import com.entity.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "order")
public class Reciver {
    @Autowired
    OrderDao orderDao;

    @RabbitHandler
    void receiver(Order order){
        try{
            System.out.println("接收到订单消息");
            orderDao.createOrder();
        }catch (Exception e){
            System.out.println("出错了");
        }
    }
}
