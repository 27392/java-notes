package cn.haohaoli.book.headfirst.decorator.version2;

import cn.haohaoli.book.headfirst.decorator.PropertiesHelper;
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

    // 描述
    private final String description;

    public Beverage(String description) {
        this.description = description;
    }

    // 调料
    private boolean milk;
    private boolean soy;
    private boolean mocha;
    private boolean whip;

    // 计算调料的钱
    public double cost(){
        double totalAmount = 0;
        if (milk) {
            totalAmount += PropertiesHelper.getPropertyForDouble("Milk");
            System.out.println("加牛奶");
        }
        if (soy) {
            totalAmount += PropertiesHelper.getPropertyForDouble("Soy");
            System.out.println("加牛豆浆");
        }
        if (mocha) {
            totalAmount += PropertiesHelper.getPropertyForDouble("Mocha");
            System.out.println("加摩卡");
        }
        if (whip) {
            totalAmount += PropertiesHelper.getPropertyForDouble("Whip");
            System.out.println("加奶泡");
        }
        return totalAmount;
    }
}
