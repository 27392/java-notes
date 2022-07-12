package cn.haohaoli.book.headfirst.state.version2.impl;

import cn.haohaoli.book.headfirst.state.version2.GumballMachine;
import cn.haohaoli.book.headfirst.state.version2.State;

/**
 * @author lwh
 */
public class SoldOutState extends State {

    public SoldOutState(GumballMachine gumballMachine) {
        super(gumballMachine);
    }

    @Override
    public void insertQuarter() {
        System.out.println("你不能投入硬币，糖果已经卖完了");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("对不起,你还没有付款");
    }

    @Override
    public void turnCrank() {
        System.out.println("你了转动了摇杆,但没有口糖果");
    }

    @Override
    public void dispense() {
        System.out.println("没有糖果");
    }
}
