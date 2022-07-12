package cn.haohaoli.book.headfirst.state.version2.impl;

import cn.haohaoli.book.headfirst.state.version2.GumballMachine;
import cn.haohaoli.book.headfirst.state.version2.State;

/**
 * @author lwh
 */
public class NoQuarterState extends State {

    public NoQuarterState(GumballMachine gumballMachine) {
        super(gumballMachine);
    }

    @Override
    public void insertQuarter() {
        System.out.println("你投入了一枚25美分硬币");
        gumballMachine.setState(gumballMachine.getHasQuarter());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("你还没有付款,无法退款");
    }

    @Override
    public void turnCrank() {
        System.out.println("你了转动了摇杆,但因为没有付款,什么反应都没有");
    }

    @Override
    public void dispense() {
        System.out.println("你还没有付款");
    }
}
