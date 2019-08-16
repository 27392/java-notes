package cn.haohaoli.book.java8.chapter11;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author LiWenHao
 * @date 2019/8/16 14:49
 */
public class Main {

    /**
     * 客户向商店查询了某种商品的价格。由于商店提供了异步API (getPriceSync)，该次调用立刻返回了一个Future对象,通过该对象客户可以在将来的某个时刻取得商品的价格。
     * 这种方式下，客户在进行商品价格查询的同时，还能执行一些其他的任务，比如查询其他家商店中商品的价格，不会呆呆地阻塞在那里等待第一家商店返回请求的结果。
     * 最后，如果所有有意义的工作都已经完成，客户所有要执行的工作都依赖于商品价格时，再调用Future的get方法。
     * 执行了这个操作后，客户要么获得Future中封装的值（如果异步任务已经完成），要么发生阻塞，直到该异步任务完成，期望的值能够访问。
     */
    public static void main(String[] args) {

        Shop shop = new Shop("沃尔玛");

        long start = System.nanoTime();

        // 异步
        Future<Double> priceSync = shop.getPriceSync("xx");

        long invocationTime = (System.nanoTime() - start) / 1_100_100;

        System.out.println("调用返回后" + invocationTime + "毫秒断开");

        // 执行其他耗时操作
        Shop.delay(3);

        try {

            // 阻塞,直到该异步任务完成
            double price = priceSync.get();
            System.out.printf("价格 : %.2f%n", price);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        long retrievalTime = (System.nanoTime() - start) / 1_100_100;

        System.out.println("返回价格 " + retrievalTime + " 毫秒");

    }
}
