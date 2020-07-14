package cn.haohaoli.book.java8.chapter10;

import lombok.Getter;
import lombok.ToString;

/**
 * @author LiWenHao
 */
public class Example {

    public static void main(String[] args) {
//        System.out.println(getCarInsuranceName1(new Person()));   // 空指针异常
        System.out.println(getCarInsuranceName2(new Person()));
        System.out.println(getCarInsuranceName3(new Person()));
    }

    @Getter
    @ToString
    private static class Person {
        private Car car;
    }

    @Getter
    @ToString
    private static class Car {
        private Insurance insurance;
    }

    @Getter
    @ToString
    private static class Insurance {
        private String name;
    }

    /**
     * 第一次尝试
     * @param person
     * @return
     */
    private static String getCarInsuranceName1(Person person) {
        return person.getCar().getInsurance().getName();
    }

    /**
     * 第二次尝试
     * @param person
     * @return
     */
    private static String getCarInsuranceName2(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    /**
     * 第三次尝试
     * @param person
     * @return
     */
    private static String getCarInsuranceName3(Person person) {
        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }
}
