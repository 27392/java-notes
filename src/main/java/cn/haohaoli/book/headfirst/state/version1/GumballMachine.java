package cn.haohaoli.book.headfirst.state.version1;

/**
 * 糖果机
 *
 * @author lwh
 */
public class GumballMachine {

    /**
     * 售罄
     */
    final static int SOLD_OUT = 0;

    /**
     * 未付款
     */
    final static int NOT_QUARTER = 1;

    /**
     * 已付款
     */
    final static int HAS_QUARTER = 2;

    /**
     * 售出
     */
    final static int SOLD = 3;

    /**
     * 默认状态为售罄
     */
    int state = SOLD_OUT;

    /**
     * 糖果数量
     */
    int count;

    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            this.state = NOT_QUARTER;
        }
    }

    /**
     * 付款
     */
    public void insertQuarter() {
        if (state == HAS_QUARTER) {
            System.out.println("你已经付款了,不能在继续付款");
        } else if (state == NOT_QUARTER) {
            state = HAS_QUARTER;
            System.out.println("你投入了一枚25美分硬币");
        } else if (state == SOLD_OUT) {
            System.out.println("你不能付款，糖果已经卖完了");
        } else if (state == SOLD) {
            System.out.println("请稍等，这是给你糖果");
        }
    }

    /**
     * 退款
     */
    public void ejectQuarter() {
        if (state == HAS_QUARTER) {
            System.out.println("退回25美分硬币");
            state = NOT_QUARTER;
        } else if (state == NOT_QUARTER) {
            System.out.println("你还没有付款");
        } else if (state == SOLD) {
            System.out.println("糖果已经售出,不能退款");
        } else if (state == SOLD_OUT) {
            System.out.println("对不起,你还没有投入付款");
        }
    }

    /**
     * 转动摇杆
     */
    public void turnCrank() {
        if (state == SOLD) {
            System.out.println("你再次转动了摇杆,糖果已经售出了.不能再给多余的糖果");
        } else if (state == NOT_QUARTER) {
            System.out.println("你了转动了摇杆, 但没有付款");
        } else if (state == SOLD_OUT) {
            System.out.println("你了转动了摇杆, 但没有口糖果");
        } else if (state == HAS_QUARTER) {
            System.out.println("你了转动了摇杆...");
            state = SOLD;
            dispense();
        }
    }

    /**
     * 糖果方法
     */
    public void dispense() {
        if (state == SOLD) {
            System.out.println("一个糖果球从槽里滚出来");
            count = count - 1;
            if (count == 0) {
                System.out.println("没糖果了!");
                state = SOLD_OUT;
            } else {
                state = NOT_QUARTER;
            }
        } else if (state == NOT_QUARTER) {
            System.out.println("你需要先付款");
        } else if (state == SOLD_OUT) {
            System.out.println("没有糖果");
        } else if (state == HAS_QUARTER) {
            System.out.println("没有糖果");
        }
    }

    @Override
    public String toString() {
        String name;
        if (state == NOT_QUARTER) {
            name = "未付款";
        } else if (state == HAS_QUARTER) {
            name = "已付款";
        } else if (state == SOLD) {
            name = "售出";
        } else {
            name = "售罄";
        }
        return "糖果机 => 糖果数量: [" + count + "], 状态: [" + name + "]";
    }
}
