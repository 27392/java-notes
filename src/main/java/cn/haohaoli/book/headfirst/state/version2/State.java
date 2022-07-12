package cn.haohaoli.book.headfirst.state.version2;

import lombok.RequiredArgsConstructor;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public abstract class State {

    protected final GumballMachine gumballMachine;

    /**
     * 付款
     */
    public abstract void insertQuarter();

    /**
     * 退款
     */
    public abstract void ejectQuarter();

    /**
     * 转动摇杆
     */
    public abstract void turnCrank();

    /**
     * 糖果方法
     */
    public abstract void dispense();
}
