package cn.haohaoli.lazy.service;

import cn.haohaoli.lazy.entity.OrderItem;

import java.util.Arrays;
import java.util.List;

/**
 * @author lwh
 */
public class OrderItemService {

    public List<OrderItem> getByOrderId(Integer orderId) {
        System.out.println("获取订单项");

        return Arrays.asList(
                new OrderItem(orderId, 1, "西瓜"),
                new OrderItem(orderId, 2, "可乐"),
                new OrderItem(orderId, 3, "薯条")
        );
    }
}
