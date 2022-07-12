package cn.haohaoli.book.headfirst.state.version2;

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
        // 转动摇杆
        gumballMachine.turnCrank();
        // 投入25美分
        gumballMachine.insertQuarter();
        // 转动摇杆
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);

    }
}
