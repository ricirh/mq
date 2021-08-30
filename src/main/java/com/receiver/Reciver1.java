package com.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "test")
public class Reciver1 {

    @RabbitHandler
    public void recive(String msg){
        System.out.println("recive data" + msg);
    }
}
