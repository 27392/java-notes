package cn.haohaoli.book.core.base.chapter9;

import lombok.*;

import java.util.*;

/**
 * 资料参考 ： https://www.cnblogs.com/CarpenterLee/p/6507161.html
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

        System.out.println("=======");

        Map<String, Integer> m = new HashMap<>();
        m.put("1", 1);
        m.put("2", 3);
        m.put("3", 3);

        //如果key空就put
        m.putIfAbsent("1", 2);

        //合并
        // m.merge("1", 3, Integer::max);
        m.merge("1", 3, (v1, v2) -> v1 > v2 ? v1 : v2);

        //如果key 不存在就获取给定的值
        System.out.println("getOrDefault : " + m.getOrDefault("99", 99));

        //根据key 替换 value
        m.replace("2", 2);

        //key,value 替换value 返回 是否替换成功
        boolean replace = m.replace("3", 3, 4);

        //替换全部 value
        m.replaceAll((k, v) -> v * 10);

        //计算
        m.compute("1", (k, v) -> v != null ? 100 : 1);

        //如果不存在
        m.computeIfAbsent("99", (v) -> 999);

        //如果存在
        m.computeIfPresent("99", (k, v) -> v / 10);

        m.forEach((k, v) -> System.out.printf("key = %s , value = %s\n", k, v));
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
