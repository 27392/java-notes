package cn.haohaoli.book.java8.chapter11;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author LiWenHao
 * @date 2019/8/16 14:36
 */
@AllArgsConstructor
public class Shop {

    private String name;

    /**
     * 获取价格
     * @param product
     * @return
     */
    public double getPrice(String product){
        return calculatePrice(product);
    }

    /**
     * 计算价格异步
     * @param product
     * @return
     */
    public Future<Double> getPriceSync(String product){

        //创建 CompletableFuture 对象, 它会包含计算的计算结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        //另一个线程异步方式计算
        new Thread(() -> {
            double price = calculatePrice(product);

            //需要长时间计算的任务结束并得出结果时,设置Future返回值
            futurePrice.complete(price);
        }).start();

        //无需等待还没有结束的计算,直接返回Future对象
        return futurePrice;
    }

    /**
     * 计算价格
     * @param product
     * @return
     */
    public double calculatePrice(String product){
        delay(1);
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟一秒的延迟
     */
    public static void delay(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
