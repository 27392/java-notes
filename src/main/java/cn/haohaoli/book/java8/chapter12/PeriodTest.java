package cn.haohaoli.book.java8.chapter12;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author LiWenHao
 */
public class PeriodTest {

    public static void main(String[] args) {
        LocalDate l1 = LocalDate.of(2019, 6, 1);
        LocalDate l2 = LocalDate.of(2020, 7, 30);

        Period period = Period.between(l1, l2);
        print(period);

    }

    private static void print(Period period) {
        System.out.println(period);
        System.out.println("getYears() : " + period.getYears());
        System.out.println("getMonths() : " + period.getMonths());
        System.out.println("getDays() : " + period.getDays());
    }
}
