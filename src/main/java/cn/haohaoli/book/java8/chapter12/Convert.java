package cn.haohaoli.book.java8.chapter12;

import java.time.*;

/**
 * @author LiWenHao
 */
public class Convert {

    public static void main(String[] args) {

        // Instant 转 LocalDate,LocalDateTime
        LocalDateTime localDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDate     localDate     = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();

        // LocalDate,LocalDateTime 转 Instant
        Instant instant1 = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant instant2 = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();

        // LocalDate 转 LocalDateTime. 转换至凌晨也就是`00:00`
        LocalDateTime localDateTime1 = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toLocalDateTime();

        // LocalDateTime 转 LocalDate,LocalTime(LocalDateTime类在内部就包含了这两个对象,自然可以很方便的转换)
        LocalDate localDate1 = LocalDateTime.now().toLocalDate();
        LocalTime localTime  = LocalDateTime.now().toLocalTime();

    }
}
