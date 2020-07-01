package cn.haohaoli.book.java8.chapter12;

import java.time.Instant;

/**
 * @author LiWenHao
 */
public class InstantTest {

    public static void main(String[] args) {

        /**
         * 创建Instant对象
         */
        create();

    }

    /**
     * 1 秒等于   1_000     毫秒
     * 1 毫秒等于 1_000_000 纳秒
     * 创建Instant对象
     */
    private static void create() {

        // 根据当前时间创建
        Instant now = Instant.now();
        System.out.println(now);

        // 使用秒从1970-01-01T00:00:00Z的纪元中获得实例.纳秒字段被设置为零
        Instant instant1 = Instant.ofEpochSecond(1);
        System.out.println(instant1);

        // 使用秒和纳秒从1970-01-01T00:00:00Z的纪元中获得实例
        Instant instant2 = Instant.ofEpochSecond(1, 1_000_000_000);
        System.out.println(instant2);

        // 使用1970-01-01T00:00:00Z纪元开始的毫秒获取Instant的实例.秒和纳秒是从指定的毫秒中提取的
        Instant instant3 = Instant.ofEpochMilli(1_000);
        System.out.println(instant3);
    }
}
