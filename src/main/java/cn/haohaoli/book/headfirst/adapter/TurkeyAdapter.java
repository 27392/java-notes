package cn.haohaoli.book.headfirst.adapter;

import cn.haohaoli.book.headfirst.adapter.duck.Duck;
import cn.haohaoli.book.headfirst.adapter.turkey.Turkey;
import lombok.AllArgsConstructor;

/**
 * 火鸡适配器
 *
 * @author LiWenHao
 * @date 2019-05-27 22:05
 */
@AllArgsConstructor
public class TurkeyAdapter implements Duck {

    private final Turkey turkey;

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
