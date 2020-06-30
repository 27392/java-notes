package cn.haohaoli.book.java8.chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

/**
 * @author LiWenHao
 */
public class LocalDateTest {

    public static void main(String[] args) {

        // 创建LocalDate
        create();

        // 获取属性
        get();

        // 通过 TemporalField 获取属性
        getFromTemporalField();

        // 判断是否是闰年
        isLeapYear();

        // 解析字符串
        parseFormString();

    }

    /**
     * 创建LocalDate
     */
    private static void create() {
        LocalDate date = LocalDate.of(2020, 5, 20);
        System.out.println(date);

        LocalDate today = LocalDate.now();
        System.out.println(today);
    }

    /**
     * 获取LocalDate属性
     * 年、月、日
     * 日期在年份中的天数
     * 星期日期
     * 年的长度,月的长度
     */
    private static void get() {

        LocalDate date = LocalDate.of(2020, 5, 20);

        // 获取年份
        int year = date.getYear();
        System.out.println("getYear() : " + year);

        // 获取月份枚举值
        Month month = date.getMonth();
        System.out.println("getMonth() : " + month);

        // 获取月份枚举值对应的数字 [ 1 - 12 ]
        int monthValue = month.getValue();
        System.out.println("getMonth().getValue() : " + monthValue);

        // 获取月的日期(在 [ 1 - 31 ] 之间)
        int dayOfMonth = date.getDayOfMonth();
        System.out.println("getDayOfMonth() : " + dayOfMonth);

        // 获取星期日期枚举值
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("getDayOfWeek() : " + dayOfWeek);

        // 获取星期日期枚举对应的数字 [ 1 - 7 ]
        int weekValue = dayOfWeek.getValue();
        System.out.println("getDayOfWeek().getValue() : " + weekValue);

        // 获取年的日期(在 [ 1 - 366 ] 之间)
        int dayOfYear = date.getDayOfYear();
        System.out.println("getDayOfYear() : " + dayOfYear);

        // --

        // 获取日期所表示的月份的长度
        int lengthOfMonth = date.lengthOfMonth();
        System.out.println("lengthOfMonth() : " + lengthOfMonth);

        // 获取日期所表示的年份的长度
        int lengthOfYear = date.lengthOfYear();
        System.out.println("lengthOfYear() : " + lengthOfYear);

    }

    /**
     * 通过 TemporalField 类来获取LocalDate属性
     * 年、月、日
     * 日期在年份中的天数
     * 星期日期
     *
     * <p>
     * 可以通过传递一个 TemporalField 参数给get方法拿到同样的信息.
     * TemporalField是一个接口,它定义了如何访问temporal对象某个字段的值。
     * ChronoField 枚举实现了这一接口,所以你可以很方便地使用get方法得到枚举元素的值
     * </p>
     */
    private static void getFromTemporalField() {

        LocalDate date = LocalDate.of(2020, 5, 20);

        // 获取年份
        int year = date.get(ChronoField.YEAR);
        System.out.println("YEAR : " + year);

        // 获取月
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("MONTH_OF_YEAR : " + month);

        // 获取日
        int day = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println("DAY_OF_MONTH : " + day);

        // 获取星期
        int week = date.get(ChronoField.DAY_OF_WEEK);
        System.out.println("DAY_OF_WEEK : " + week);

        // 获取年的日期
        int dayOfYear = date.get(ChronoField.DAY_OF_YEAR);
        System.out.println("DAY_OF_YEAR : " + dayOfYear);

    }

    /**
     * 判断是否是闰年
     */
    private static void isLeapYear() {
        LocalDate date     = LocalDate.of(2020, 5, 20);
        boolean   leapYear = date.isLeapYear();

        System.out.println("isLeapYear() : " + leapYear);
    }

    /**
     * 通过字符串解析
     * 注意:一旦传递的字符串参数无法被解析为合法的 LocalDate 对象,
     * `parse`方法都会抛出一个继承自`RuntimeException`的`DateTimeParseException`异常
     */
    private static void parseFormString() {
        System.out.println(LocalDate.parse("2020-12-1")); // 错误 DateTimeParseException
        System.out.println(LocalDate.parse("2020-12-01"));
    }

}
