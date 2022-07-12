package cn.haohaoli.book.headfirst.state.version1;

/**
 * @author lwh
 */
public class Test {

    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);
        System.out.println(gumballMachine);

        // 投入25美分
        gumballMachine.insertQuarter();
        // 转动摇杆
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);

        // 投入25美分
        gumballMachine.insertQuarter();
        // 退钱
        gumballMachine.ejectQuarter();
        // 转动摇杆
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);

        // 投入25美分
        gumballMachine.insertQuarter();
        // 转动摇杆
        gumballMachine.turnCrank();
        // 投入25美分
        gumballMachine.insertQuarter();
        // 转动摇杆
        gumballMachine.turnCrank();
        // 退钱
        gumballMachine.ejectQuarter();
        System.out.println(gumballMachine);

        // 投入25美分
        gumballMachine.insertQuarter();
        // 投入25美分
        gumballMachine.insertQuarter();
        // 转动摇杆
        gumballMachine.turnCrank();
        // 投入25美分
        gumballMachine.insertQuarter();
        // 转动摇杆
        gumballMachine.turnCrank();
        // 投入25美分
        gumballMachine.insertQuarter();
        // 转动摇杆
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);
    }
}
