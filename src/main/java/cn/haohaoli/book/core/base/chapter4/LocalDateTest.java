package cn.haohaoli.book.core.base.chapter4;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * java8 LocalDate类
 * @see LocalDate
 * @author liWenHao
 * @date 2019/1/12 09:53
 */
public class LocalDateTest {

    public static void main(String[] args) {

        /**
         * TODO LocalDate 概要
         *  构造对象：
         *      不要使用构造器来构造 LocalDate 类的对象。实际上
         *      应当使用静态工厂方法 (factory method) 代表你调用构造器。
         *        now()                                     从默认时区的系统时钟获取当前日期。
         *        of(int year, int month, int dayOfMonth)   从一年，一个月和一天获得一个 LocalDate的实例。
         *  方法：
         *      getYear()                       获取年份字段。
         *      getMonthValue()                 将月份字段从1到12。
         *      getDayOfMonth()                 获得日期字段。
         *      plusYears(long yearsToAdd)      返回这个 LocalDate的副本，其中指定的时间段以添加的年数表示。
         *      plusDays(long daysToAdd)        返回指定天数的 LocalDate的副本。
         *      minusDays(long daysToSubtract)  返回此 LocalDate的副本，并减去指定的天数。
         */
        LocalDate now = LocalDate.now();
        print(now);
        LocalDate newYear = LocalDate.of(2020, 11, 11);
        print(newYear);
        print(newYear.plusYears(100));
        calendar();
    }

    /**
     * 日历方法
     */
    private static void calendar() {

        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();
        //设置时间为本月一号
        date = date.minusDays(today - 1);

        //获取是星期几
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int value = dayOfWeek.getValue();

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 0; i < value; i++) {
            System.out.printf("%3d", 0);
        }

        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today)
                System.out.print("*");
            else
                System.out.print(" ");
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1) System.out.println();
        }
    }

    private static void print(LocalDate date) {
        int year = date.getYear();
        /**
         * 获取月份内容
         * getMonthValue()
         * getMonth().getValue();
         */
        int monthValue = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();

        /**
         * TODO printf 输出操作
         *  https://www.cnblogs.com/seakt/p/4478045.html
         *      %c        单个字符
         *      %d        十进制整数
         *      %f        十进制浮点数
         *      %o        八进制数
         *      %s        字符串
         *      %u        无符号十进制数
         *      %x        十六进制数
         *      %%        输出百分号%
         */
        System.out.printf("%d - %d - %d \n", year, monthValue, dayOfMonth);
    }
}
