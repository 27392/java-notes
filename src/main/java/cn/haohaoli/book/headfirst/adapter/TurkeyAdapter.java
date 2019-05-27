package cn.haohaoli.book.headfirst.adapter;

import lombok.AllArgsConstructor;

import java.util.stream.IntStream;

/**
 * @author LiWenHao
 * @date 2019-05-27 22:05
 */
@AllArgsConstructor
public class TurkeyAdapter implements Duck {

    private Turkey turkey;

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        IntStream.rangeClosed(0, 5).forEach(i -> turkey.fly());
    }
}
