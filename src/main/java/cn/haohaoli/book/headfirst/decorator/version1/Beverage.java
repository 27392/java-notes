package cn.haohaoli.book.headfirst.decorator.version1;

import lombok.Getter;

/**
 * 饮料
 * @author LiWenHao
 * @date 2019-03-11 19:58
 */
@Getter
public abstract class Beverage {

    //描述
    private final String description;

    public Beverage(String description) {
        this.description = description;
    }

    //价格
    public abstract double cost();
}
