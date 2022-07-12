package cn.haohaoli.book.headfirst.state.version2.impl;

import cn.haohaoli.book.headfirst.state.version2.GumballMachine;
import cn.haohaoli.book.headfirst.state.version2.State;

/**
 * @author lwh
 */
public class WinnerState extends State {

    public WinnerState(GumballMachine gumballMachine) {
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
        System.out.println("中大奖了! 本次你可以得到两个糖果");
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() == 0) {
            gumballMachine.setState(gumballMachine.getSoldOut());
        } else {
            gumballMachine.releaseBall();
            if (gumballMachine.getCount() == 0) {
                gumballMachine.setState(gumballMachine.getSoldOut());
            } else {
                gumballMachine.setState(gumballMachine.getNotQuarter());
            }
        }
    }
}
