package cn.haohaoli.book.java8.chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;

/**
 * @author LiWenHao
 */
public class TemporalAdjustersTest {

    /*
        dayOfWeekInMonth            创建一个新的日期，它的值为同一个月中每一周的第几天
        firstDayOfMonth             创建一个新的日期，它的值为当月的第一天
        firstDayOfNextMonth         创建一个新的日期，它的值为下月的第一天
        firstDayOfNextYear          创建一个新的日期，它的值为明年的第一天
        firstDayOfYear              创建一个新的日期，它的值为当年的第一天
        firstInMonth                创建一个新的日期，它的值为同一个月中，第一个符合星期几要求的值
        lastDayOfMonth              创建一个新的日期，它的值为当月的最后一天
        lastDayOfNextMonth          创建一个新的日期，它的值为下月的最后一天
        lastDayOfNextYear           创建一个新的日期，它的值为明年的最后一天
        lastDayOfYear               创建一个新的日期，它的值为今年的最后一天
        lastInMonth                 创建一个新的日期，它的值为同一个月中，最后一个符合星期几要求的值
        next/previous               创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期
        nextOrSame/previousOrSame   创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期，如果该日期已经符合要求，直接返回该对象
     */
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();

        // 调整到下周一
        LocalDate with = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(with);

        // 调整到今年最后一天
        LocalDate with1 = now.with(TemporalAdjusters.lastDayOfYear());
        System.out.println(with1);

        // 调整到今年第一天
        LocalDate with2 = now.with(TemporalAdjusters.firstDayOfYear());
        System.out.println(with2);

        // 调整到当月的第一天
        LocalDate with3 = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(with3);

        // 自定义 TemporalAdjuster
        customAdjust();
    }

    private static void customAdjust() {
        LocalDate now = LocalDate.now();

        // 使用自定义 TemporalAdjuster
        LocalDate with = now.with(new NextWorkingDay());
        System.out.println(with);

        // 使用 lambda
        LocalDate with1 = now.with(temporal -> {
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int       dayToAdd  = 1;
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                dayToAdd = 3;
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        // 使用 ofDateAdjuster
        LocalDate with2 = now.with(TemporalAdjusters.ofDateAdjuster(temporal -> {
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int       dayToAdd  = 1;
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                dayToAdd = 3;
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }));
    }


    /**
     * 请设计一个NextWorkingDay类，该类实现了TemporalAdjuster接口，能够计算明天的日期，同时过滤掉周六和周日这些节假日。
     * 格式如下所示：
     * date = date.with(new NextWorkingDay());
     * 如果当天的星期介于周一至周五之间，日期向后移动一天；如果当天是周六或者周日，则返回下一个周一
     */
    static class NextWorkingDay implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int       dayToAdd  = 1;
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                dayToAdd = 3;
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }
}
