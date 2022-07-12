package cn.haohaoli.book.headfirst.state.version2;

import cn.haohaoli.book.headfirst.state.version2.impl.*;
import lombok.Getter;

/**
 * 糖果机
 *
 * @author lwh
 */
@Getter
public class GumballMachine {

    private State soldOut;
    private State notQuarter;
    private State hasQuarter;
    private State sold;
    private State winner;

    private State state;
    private int   count;

    public GumballMachine(int count) {
        this.soldOut = new SoldOutState(this);
        this.notQuarter = new NoQuarterState(this);
        this.hasQuarter = new HasQuarterState(this);
        this.sold = new SoldState(this);
        this.winner = new WinnerState(this);
        this.count = count;
        if (count > 0) {
            this.state = notQuarter;
        } else {
            this.state = soldOut;
        }
    }

    /**
     * 付款
     */
    public void insertQuarter() {
        state.insertQuarter();
    }

    /**
     * 退款
     */
    public void ejectQuarter() {
        state.ejectQuarter();
    }

    /**
     * 转动摇杆
     */
    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    /**
     * 释放数量
     */
    public void releaseBall() {
        if (count != 0) {
            count = count - 1;
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        String name;
        if (state == notQuarter) {
            name = "未付款";
        } else if (state == hasQuarter) {
            name = "已付款";
        } else if (state == sold) {
            name = "售出";
        } else {
            name = "售罄";
        }
        return "糖果机 => 糖果数量: [" + count + "], 状态: [" + name + "]";
    }
}
