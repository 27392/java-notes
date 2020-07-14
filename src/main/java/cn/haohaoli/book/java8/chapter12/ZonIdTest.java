package cn.haohaoli.book.java8.chapter12;

import java.time.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author LiWenHao
 */
public class ZonIdTest {

    public static void main(String[] args) {

        // 打印可用的时区
        printAvailableZoneIds();

        // 添加时区信息
        addTimeZoneInfo();

    }

    /**
     * 打印可用时区id
     */
    private static void printAvailableZoneIds() {
        Set<String>     availableZoneIds = ZoneId.getAvailableZoneIds();
        TreeSet<String> strings          = new TreeSet<>(availableZoneIds);
        strings.forEach(System.out::println);
    }

    /**
     * 添加时区信息
     */
    private static void addTimeZoneInfo() {

        // 系统默认时区
        ZoneId systemDefaultZoneId = ZoneId.systemDefault();
        System.out.println("系统默认时区: " + systemDefaultZoneId);

        Instant       instant        = Instant.now();
        ZonedDateTime zonedDateTime2 = instant.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime2);

        LocalDate     now           = LocalDate.now();
        ZonedDateTime zonedDateTime = now.atStartOfDay(systemDefaultZoneId);
        System.out.println(zonedDateTime);

        LocalDateTime localDateTime  = LocalDateTime.now();
        ZonedDateTime zonedDateTime1 = localDateTime.atZone(systemDefaultZoneId);
        System.out.println(zonedDateTime1);

    }
}
