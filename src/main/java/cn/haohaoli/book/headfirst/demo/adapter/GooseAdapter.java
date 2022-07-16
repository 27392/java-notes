package cn.haohaoli.book.headfirst.demo.adapter;

import cn.haohaoli.book.headfirst.demo.AbsteractQuackable;
import cn.haohaoli.book.headfirst.demo.Goose;
import lombok.RequiredArgsConstructor;

/**
 * 鹅适配器
 *
 * @author lwh
 */
@RequiredArgsConstructor
public class GooseAdapter extends AbsteractQuackable {

    private final Goose goose;

    @Override
    protected void doQuack() {
        goose.honk();
    }

    public Goose getGoose() {
        return goose;
    }
}
