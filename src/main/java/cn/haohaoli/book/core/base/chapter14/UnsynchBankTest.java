package cn.haohaoli.book.core.base.chapter14;

/**
 * @author LiWenHao
 * @date 2019/9/19 10:48
 */
public class UnsynchBankTest {

    public static final int    ACCOUNTS        = 2;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT      = 5*1000;
    public static final int    DELAY           = 10;

    public static void main(String[] args) {
        // 初始化一百个账号,里面初始化1000
        Bank bank = new Bank(ACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < ACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int    toAccount = (int) (bank.size() * Math.random());
                        double amount    = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            };
            new Thread(r).start();
        }
    }

}
