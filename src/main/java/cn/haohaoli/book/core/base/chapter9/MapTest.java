package cn.haohaoli.book.core.base.chapter9;

import lombok.*;

import java.util.*;

/**
 * @author LiWenHao
 * @date 2019-05-26 18:33
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, Employee> staff = new HashMap<>();

        staff.put("144-25-5464", Employee.of("Amy Laa"));
        staff.put("567-24-2546", Employee.of("Harry Hacker"));
        staff.put("157-22-3244", Employee.of("Gary Cooper"));
        staff.put("543-32-3422", Employee.of("Francesca Cruz"));

        System.out.println(staff);

        staff.remove("567-24-2546");

        staff.put("543-32-3422", Employee.of("Francesca Miller"));

        System.out.println("get : =" + staff.get("157-22-3244"));

        staff.forEach((k, v) -> System.out.printf("key = %s , value = %s\n", k, v));

        System.out.println(staff.getOrDefault("123-12-1234", Employee.of("default")));

        System.out.println("containsKey : " + staff.containsKey("144-25-5464"));

        System.out.println("containsValue : " + staff.containsValue(Employee.of("Gary Cooper")));

        Map<String, Employee> map = new HashMap<String, Employee>() {
            {
                put("666-66-666", new Employee("Six"));
            }
        };

        map.putAll(staff);
        map.forEach((k, v) -> System.out.printf("key = %s , value = %s\n", k, v));

    }


    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor(staticName = "of")
    private static class Employee implements Comparable<Employee> {

        private String name;

        @Override
        public int compareTo(Employee o) {
            return this.name.compareTo(o.getName());
        }
    }
}
