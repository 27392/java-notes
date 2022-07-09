package cn.haohaoli.book.headfirst.decorator.version3;

import lombok.Getter;

/**
 * 饮料
 * @author LiWenHao
 * @date 2019-03-12 21:18
 */
@Getter
public abstract class Beverage {

    /**
     * 描述
     */
    private final String description;

    public Beverage(String description) {
        this.description = description;
    }

    /**
     * 计算费用
     * @return  总费用
     */
    public abstract double cost();

}
