package cn.haohaoli.lazy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lwh
 */
@Data
@AllArgsConstructor
public class OrderItem {

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;
}
