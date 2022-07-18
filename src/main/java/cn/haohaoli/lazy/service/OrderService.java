package cn.haohaoli.lazy.service;

import cn.haohaoli.lazy.componet.LoaderMap;
import cn.haohaoli.lazy.componet.LazyMethodInterceptor;
import cn.haohaoli.lazy.entity.Order;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author lwh
 */
public class OrderService {

    private final UserService      userService  = new UserService();
    private final OrderItemService orderItemService = new OrderItemService();

    public Order getOrder() {

        LoaderMap loaderMap = new LoaderMap();

        // 创建代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Order.class);
        enhancer.setCallback(new LazyMethodInterceptor(loaderMap));
        Order order = (Order) enhancer.create();
        order.setId(66);
        order.setPrice(100.0);
        order.setUserId(1);

        loaderMap.add("user", order, () -> userService.getById(order.getUserId()));
        loaderMap.add("items", order, () -> orderItemService.getByOrderId(order.getId()));

        return order;
    }


}
