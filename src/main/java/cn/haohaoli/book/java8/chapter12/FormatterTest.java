package cn.haohaoli.book.java8.chapter12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author LiWenHao
 */
public class FormatterTest {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.of(2020, 7, 14);

        System.out.println(localDate.format(DateTimeFormatter.BASIC_ISO_DATE)); // 20200714
        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)); // 2020-07-14

        System.out.println(LocalDate.parse("20200715", DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(LocalDate.parse("2020-07-14", DateTimeFormatter.ISO_LOCAL_DATE));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime     localDateTime     = LocalDateTime.now();

        System.out.println(localDateTime.format(dateTimeFormatter));

    }
}
