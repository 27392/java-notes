package cn.haohaoli.book.java8.chapter12;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

/**
 * @author LiWenHao
 */
public class LocalTimeTest {

    public static void main(String[] args) {

        // 创建LocalTime
        create();

        // 获取属性
        get();

        // 通过 TemporalField 获取属性
        getFromTemporalField();

        // 解析字符串
        parseFormString();
    }

    /**
     * 创建LocalTime
     */
    private static void create() {
        LocalTime time = LocalTime.of(18, 50, 30);
        System.out.println(time);

        LocalTime now = LocalTime.now();
        System.out.println(now);
    }

    /**
     * 获取LocalDate属性
     * 年、月、日
     * 日期在年份中的天数
     * 星期日期
     * 年的长度,月的长度
     */
    private static void get() {

        LocalTime time = LocalTime.of(18, 50, 30);

        // 获取时
        int hour = time.getHour();
        System.out.println("getHour() : " + hour);

        // 获取分
        int minute = time.getMinute();
        System.out.println("getMinute() : " + minute);

        // 获取秒
        int second = time.getSecond();
        System.out.println("getSecond() : " + second);

    }

    /**
     * 通过 TemporalField 类来获取LocalTime属性
     * 时、分、秒
     */
    private static void getFromTemporalField() {

        LocalTime time = LocalTime.of(18, 50, 30);

        // 获取小时,[0 - 23].以 24 小时计算
        int hour = time.get(ChronoField.HOUR_OF_DAY);
        System.out.println("HOUR_OF_DAY : " + hour);

        // 获取小时,[0 - 11].以 12 小时计算
        int hourOfAmPm = time.get(ChronoField.HOUR_OF_AMPM);
        System.out.println("HOUR_OF_AMPM : " + hourOfAmPm);

        // 获取分钟,[0 - 59]
        int minuteOfHour = time.get(ChronoField.MINUTE_OF_HOUR);
        System.out.println("MINUTE_OF_HOUR : " + minuteOfHour);

        // 获取一天中的分钟
        int minuteOfDay = time.get(ChronoField.MINUTE_OF_DAY);
        System.out.println("MINUTE_OF_DAY : " + minuteOfDay);

        // 获取秒数,[0 - 59]
        int secondOfMinute = time.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println("SECOND_OF_MINUTE : " + secondOfMinute);

        // 获取一天中的秒数
        int secondOfDay = time.get(ChronoField.SECOND_OF_DAY);
        System.out.println("SECOND_OF_DAY : " + secondOfDay);

    }

    /**
     * 通过字符串解析
     * 注意:一旦传递的字符串参数无法被解析为合法的 LocalTime 对象,
     * `parse`方法都会抛出一个继承自`RuntimeException`的`DateTimeParseException`异常
     */
    private static void parseFormString() {
//        System.out.println(LocalTime.parse("23:59:9")); // 错误 DateTimeParseException
        System.out.println(LocalTime.parse("23:59:09"));
    }
}
