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

            /**
             * TODO CompletableFuture 异常抛出
             *  如果没有意外,我们目前开发的代码工作得很正常。但是,如果价格计算过程中产生了错误
             *  这种情况下你会得到一个相当糟糕的结果：用于提示错误的异常会被限制在试图计算商品价格的当前线程的范围内
             *  最终会杀死该线程,而这会导致等待get方法返回结果的客户端永久地被阻塞
             *   客户端可以使用重载版本的get方法，它使用一个超时参数来避免发生这样的情况。
             *  种值得推荐的做法，你应该尽量在你的代码中添加超时判断的逻辑，避免发生类似的问题。
             *  使用这种方法至少能防止程序永久地等待下去，超时发生时,程序会得到通知发生了TimeoutException
             *   不过，也因为如此，你不会有机会发现计算商品价格的线程内到底发生了什么问题才引发了这样的失效。
             *  为了让客户端能了解商店无法提供请求商品价格的原因，你需要使用
             *  CompletableFuture的completeExceptionally方法将导致CompletableFuture内发生问题的异常抛出
             *  客户端现在会收到一个ExecutionException异常，该异常接收了一个包含失败原因的Exception参数
             */
            try {
                double price = calculatePrice(product);

                //需要长时间计算的任务结束并得出结果时,设置Future返回值
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
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
