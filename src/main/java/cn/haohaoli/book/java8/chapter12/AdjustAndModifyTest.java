package cn.haohaoli.book.java8.chapter12;

import java.time.LocalDate;
import java.time.temporal.*;

/**
 * @author LiWenHao
 */
public class AdjustAndModifyTest {

    public static void main(String[] args) {

        // 调整日期
        with();

        // 增加减少日期
        plusAndMinus();

    }


    /**
     * 加日期和减日期
     */
    private static void plusAndMinus() {
        LocalDate now = LocalDate.now();
        System.out.println("now: " + now);

        // 加一周
        LocalDate localDate = now.plusWeeks(1);
        System.out.println("plusWeeks: " + localDate);

        // 加三年
        LocalDate localDate1 = now.plusYears(3);
        System.out.println("plusYears " + localDate1);

        // 减五天
        LocalDate localDate2 = now.minusDays(5);
        System.out.println("minusDays: " + localDate2);

        // 加六天
        LocalDate plus = now.plus(6, ChronoUnit.DAYS);
        System.out.println("plus: " + plus);

    }

    /**
     * 修改日期
     */
    private static void with() {
        LocalDate now = LocalDate.now();
        System.out.println("now: " + now);

        // 修改年为 2019
        LocalDate localDate = now.withYear(2019);
        System.out.println("withYear: " + localDate);

        // 修改日为 15
        LocalDate localDate1 = now.withDayOfMonth(15);
        System.out.println("withDayOfMonth: " + localDate1);

        // 修改月为 3
        LocalDate localDate2 = now.withMonth(3);
        System.out.println("withMonth: " + localDate2);

        // 修改月为 12
        LocalDate with = now.with(ChronoField.MONTH_OF_YEAR, 12);
        System.out.println("with MONTH_OF_YEAR : " + with);
    }

}
