package cn.haohaoli.book.core.chapter4;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class localDateTest {

    public static void main(String[] args) {

        LocalDate date = LocalDate.now();
        //月
        int month = date.getMonthValue();
        //日
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
}
