package cn.haohaoli.book.headfirst.decorator.version1;

import lombok.Getter;
import lombok.Setter;

/**
 * 饮料
 * @author LiWenHao
 * @date 2019-03-11 19:58
 */
@Getter
@Setter
public abstract class Beverage {

    //描述
    protected String description;

    //价格
    protected abstract double cost();
}
