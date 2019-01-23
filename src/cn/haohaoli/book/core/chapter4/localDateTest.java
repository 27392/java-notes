package cn.haohaoli.book.core.chapter4;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * java8 LocalDate类
 * @see LocalDate
 * @author liWenHao
 * @date 2019/1/12 09:53
 */
public class localDateTest {

    public static void main(String[] args) {

        /**
         * 不要使用构造器来构造 LocalDate 类的对象。实际上，应当使用静态工厂方法 (factory method) 代表你调用构造器。
         */
        LocalDate now = LocalDate.now();
        print(now);
        LocalDate newYear = LocalDate.of(2020, 11, 11);
        print(newYear);
        print(newYear.plusYears(100));
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
        //获取年
        int year = date.getYear();
        //获取月份
        int monthValue = date.getMonthValue();
        //获取日
        int dayOfMonth = date.getDayOfMonth();
        /**
         * https://www.cnblogs.com/seakt/p/4478045.html
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
