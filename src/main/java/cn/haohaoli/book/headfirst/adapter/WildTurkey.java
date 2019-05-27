package cn.haohaoli.book.headfirst.adapter;

/**
 * 野生火鸡
 * @author LiWenHao
 * @date 2019-05-27 22:02
 */
public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("火鸡叫");
    }

    @Override
    public void fly() {
        System.out.println("短距离飞行");
    }
}
