package cn.haohaoli.lazy.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lwh
 */
@Getter
@Setter
public class Order {

    private Integer id;

    private Double price;

    private User user;

    private Integer userId;

    private List<OrderItem> items;
}
