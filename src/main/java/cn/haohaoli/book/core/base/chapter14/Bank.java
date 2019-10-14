package cn.haohaoli.book.core.base.chapter14;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiWenHao
 * @date 2019/9/19 10:49
 */
public class Bank {
    private final double[]      accounts;
    private       ReentrantLock bankLock;
    private       Condition     sufficientFunds;

    public Bank(int n, double initialBalance) {
        this.accounts = new double[n];
        Arrays.fill(this.accounts, initialBalance);
        this.bankLock        = new ReentrantLock();
        this.sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int form, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            if (accounts[form] < amount) {
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread());
            accounts[form] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, form, to);
            accounts[to] += amount;
            System.out.printf(" Total balance : %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }

    private double getTotalBalance() {
        bankLock.lock();
        try {
            return Arrays.stream(accounts).sum();
        } finally {
            bankLock.unlock();
        }
    }

    public static void main(String[] args) {

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("");
        });
    }
}
