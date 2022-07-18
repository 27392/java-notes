package cn.haohaoli.lazy;

import cn.haohaoli.lazy.entity.Order;
import cn.haohaoli.lazy.service.OrderService;

import java.lang.reflect.Field;

/**
 * @author lwh
 */
public class LazyTest {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        Order        order        = orderService.getOrder();

        get(order, "user");
        get(order, "items");

        order.getUser();
        order.getUser();
        get(order, "user");

        order.getItems();
        order.getItems();
        get(order, "items");
    }

    public static void get(Order order, String property) {
        Class<?> superclass = order.getClass().getSuperclass();
        if (superclass == Order.class) {
            try {
                Field declaredField = superclass.getDeclaredField(property);
                declaredField.setAccessible(true);
                Object o = declaredField.get(order);
                System.out.println(property + ": " + o);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
