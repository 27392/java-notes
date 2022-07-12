package cn.haohaoli.book.headfirst.state.version2.impl;

import cn.haohaoli.book.headfirst.state.version2.GumballMachine;
import cn.haohaoli.book.headfirst.state.version2.State;

import java.util.Random;

/**
 * @author lwh
 */
public class HasQuarterState extends State {

    private final Random randomWinner = new Random(System.currentTimeMillis());

    public HasQuarterState(GumballMachine gumballMachine) {
        super(gumballMachine);
    }

    @Override
    public void insertQuarter() {
        System.out.println("你已经付款,无法在继续付款");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("退回25美分硬币");
        gumballMachine.setState(gumballMachine.getNotQuarter());
    }

    @Override
    public void turnCrank() {
        System.out.println("你了转动了摇杆...");
        int winner = randomWinner.nextInt(10);
        if (winner == 0 && gumballMachine.getCount() > 1) {
            gumballMachine.setState(gumballMachine.getWinner());
        } else {
            gumballMachine.setState(gumballMachine.getSold());
        }
    }

    @Override
    public void dispense() {
        System.out.println("没有糖果");
    }
}
