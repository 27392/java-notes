package cn.haohaoli.book.headfirst.decorator.version2;

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
    protected String description = "未知的饮料";

    //调料
    protected boolean milk;
    protected boolean soy;
    protected boolean mocha;
    protected boolean whip;


    private final double milkAmount = 2d;
    private final double soyAmount = 1d;
    private final double mochaAmount = 2.5d;
    private final double whipAmount = 1.5d;

    //计算调料的钱
    protected double cost(){
        double totalAmount = 0;
        if (milk) {
            totalAmount += milkAmount;
        }
        if (soy) {
            totalAmount += soyAmount;
        }
        if (mocha) {
            totalAmount += mochaAmount;
        }
        if (whip) {
            totalAmount += whipAmount;
        }
        return totalAmount;
    };
}
