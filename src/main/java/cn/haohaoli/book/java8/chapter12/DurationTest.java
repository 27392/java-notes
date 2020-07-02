package cn.haohaoli.book.java8.chapter12;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author LiWenHao
 */
public class DurationTest {

    public static void main(String[] args) {

        LocalTime ld1 = LocalTime.of(18, 20);
        LocalTime ld2 = LocalTime.of(20, 30);

        Duration d1 = Duration.between(ld1, ld2);
        print(d1);

        LocalDateTime dt1 = LocalDateTime.of(2020, 6, 5, 20, 1);
        LocalDateTime dt2 = LocalDateTime.of(2020, 7, 5, 20, 1);

        Duration d2 = Duration.between(dt1, dt2);
        print(d2);

    }

    private static void print(Duration duration) {
        System.out.println(duration);
        System.out.println("toNanos() : " + duration.toNanos());
        System.out.println("toMillis() : " + duration.toMillis());
        System.out.println("toMinutes() : " + duration.toMinutes());
        System.out.println("toHours() : " + duration.toHours());
        System.out.println("toDays() : " + duration.toDays());
    }
}
