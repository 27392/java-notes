package cn.haohaoli.book.headfirst.state.version2.impl;

import cn.haohaoli.book.headfirst.state.version2.GumballMachine;
import cn.haohaoli.book.headfirst.state.version2.State;

/**
 * @author lwh
 */
public class SoldState extends State {

    public SoldState(GumballMachine gumballMachine) {
        super(gumballMachine);
    }

    @Override
    public void insertQuarter() {
        System.out.println("请稍等，这是给你糖果");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("糖果已经售出,不能退款");
    }

    @Override
    public void turnCrank() {
        System.out.println("你再次转动了摇杆,糖果已经售出了.不能再给多余的糖果");
    }

    @Override
    public void dispense() {
        System.out.println("一个糖果球从槽里滚出来");
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() == 0) {
            System.out.println("没糖果了!");
            gumballMachine.setState(gumballMachine.getSoldOut());
        } else {
            gumballMachine.setState(gumballMachine.getNotQuarter());
        }
    }
}
