package com.controller;

import com.entity.Goods;
import com.entity.Order;
import com.entity.User;
import com.server.OrderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderServer orderServer;


    @GetMapping("/remain")
    public Object remain(Goods good){
        Map map = new HashMap();
        map.put("code",200);
        try{
            map.put("remain",orderServer.getRemain(good));
            map.put("statu", "success");
        }catch (Exception e){
            map.put("statu","failed in querying");
        }
        return map;
    }

    @PostMapping("/purchase")
    public Object purchase(User user, Goods good){
        Map map = new HashMap();
        map.put("code",200);
        try{
            if(orderServer.createOrder(user,good)){
                map.put("inform","下单成功");
            }else{
                map.put("inform","抢完了");
            }
        }catch (Exception e){
            map.put("statu","failed in querying");
        }
        return map;
    }

    @PostMapping("/canelOrder")
    public Object cancelOrder(User user, Order order){
        Map map = new HashMap();
        map.put("code",200);
        try{
            orderServer.cancelOrder(user,order);
        }catch (Exception e){
            map.put("statu","failed in Operating database");
        }

        map.put("inform","已经取消订单");

        return map;
    }
}
