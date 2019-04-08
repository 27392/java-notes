package cn.haohaoli.book.headfirst.decorator.version1;

import cn.haohaoli.book.headfirst.decorator.version1.combination.DecafWithSteamedMilkAndSoy;

/**
 * @author LiWenHao
 * @date 2019-04-08 16:41
 */
public class Test {

    public static void main(String[] args) {
        DecafWithSteamedMilkAndSoy decafWithSteamedMilkAndSoy = new DecafWithSteamedMilkAndSoy();
        double cost = decafWithSteamedMilkAndSoy.cost();
        System.out.println(decafWithSteamedMilkAndSoy.getDescription());
        System.out.println(cost);
    }
}
