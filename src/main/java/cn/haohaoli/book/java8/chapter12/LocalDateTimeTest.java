package cn.haohaoli.book.java8.chapter12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * @author LiWenHao
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {

        // 创建 LocalDateTime 对象
        create();

        // 提取`LocalDate`和`LocalTime`对象
        extract();

        // 获取属性
        get();

    }

    /**
     * 创建LocalDateTime
     * LocalDateTime,是`LocalDate`和`LocalTime`的合体
     * 它同时表示了日期和时间,但不带有时区信息,你可以直接创建,也可以通过合并日期和时间对象构造
     * 也可以分别使用`LocalDate`的`atTime`方法和`LocalTime`的`atDate`方法来创建
     */
    public static void create() {

        // 通过全参方法创建,其内部还是转换成了LocalDate,LocalTime对象
        LocalDateTime dt  = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt1 = LocalDateTime.of(2014, 3, 18, 13, 45, 20);
        System.out.println("of : " + dt);
        System.out.println("of : " + dt1);

        // 使用 LocalDate , LocalTime 创建
        LocalDate     date = LocalDate.of(2020, 5, 20);
        LocalTime     time = LocalTime.of(12, 0, 0);
        LocalDateTime dt2  = LocalDateTime.of(date, time);
        System.out.println("of : " + dt2);

        // 使用 LocalDate 对象的 atTime 方法创建
        LocalDateTime dt3 = LocalDate.now().atTime(LocalTime.of(12, 0, 0));
        System.out.println("LocalDate.atTime() : " + dt3);

        // 使用 LocalTime 对象的 atDate 方法创建
        LocalDateTime dt4 = LocalTime.now().atDate(LocalDate.of(2020, 5, 20));
        System.out.println("LocalTime.atDate() : " + dt4);

        // 使用 now 方法创建
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now : " + now);

    }

    /**
     * 提取
     * LocalDateTime类中包含了`LocalDate`和`LocalTime`对象的应用,
     * 可以从中获取`LocalDate`和`LocalTime`对象
     */
    private static void extract() {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("now : " + now);

        // 从 LocalDateTime 中提取 LocalDate
        LocalDate localDate = now.toLocalDate();
        System.out.println(localDate);

        // 从 LocalDateTime 中提取 LocalTime
        LocalTime localTime = now.toLocalTime();
        System.out.println(localTime);
    }

    /**
     * 获取属性
     * `LocalDate`和`LocalTime`能获取的属性`LocalDateTime`都可以获取
     */
    private static void get() {
        LocalDateTime now = LocalDateTime.now();

        // 实际调用的是LocalDate类中的`getYear`方法
        int year = now.getYear();
        System.out.println("getYear()" + year);

        // 实际调用的是LocalTime类中的`getHour`方法
        int hour = now.getHour();
        System.out.println("getHour()" + hour);

    }
}
