package cn.haohaoli.book.core.chapter6.lambda;

import cn.haohaoli.book.core.chapter5.inheritance.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * @author liWenHao
 * @date 2019/1/5 16:43
 */
public class LambdaTest {

    /**
     * 接口            返回值   备注
     * Supplier<T>     void     提供一个T类型的值
     * Consumer<T>     T        处理一个T类型的值
     * Function<T,R>   R        有一个T类型
     * Predicate<T>    boolean  布尔值函数
     */
    public static void main(String[] args) {

        Comparator<String> stringComparator = ((o1, o2) -> o1.length() - o2.length());

        List<Employee> staff = new ArrayList<>(100);
        staff.add(new Employee("yxx", 75000, 1987, 12, 15));
        staff.add(new Employee("lxx", 50000, 1989, 10, 1));
        staff.add(new Employee("xxs", 40000, 1990, 3, 15));
        System.out.println(staff);
        List<Employee> filter = filter(staff, (o) -> o.getSalary() > 45000);
        Supplier<Person> supplier = Person::new;
        supplier.get();
        System.out.println("filter:  " + filter);
        BiFunction<String, String, Integer> biFunction = (s, s1) -> s.length() - s1.length();
        Integer apply = biFunction.apply("111", "22");
        System.out.println(apply);
        staff.removeIf(employee -> employee.getName().equals("c"));
        System.out.println(staff);

        //方法引用
        String[] s = {"xxx", "sss", "ff", "ggggg"};
        Arrays.sort(s, String::compareToIgnoreCase);

        System.out.println(Arrays.toString(s));
        List<String> strings = Arrays.asList(s);
        Stream<Person> personStream = strings.stream().map(Person::new);
        Person[] people = personStream.toArray(Person[]::new);
        Arrays.stream(people).forEach(System.out::println);
        repeat(10, () -> System.out.println("Hello World"));
        /**
         * 尽量使用IntConsumer等来减少自动装箱
         */
        repeat(10, (i) -> System.out.println(i + " Hello World"));
    }

    public static void repeat(int i, Runnable runnable) {
        for (int j = 0; j < i; j++) {
            runnable.run();
        }
    }

    public static void repeat(int i, IntConsumer intConsumer) {
        for (int j = 0; j < i; j++) {
            intConsumer.accept(j);
        }
    }

    public static List<Employee> filter(List<Employee> employees, Predicate<Employee> predicate) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if (predicate.test(employee)) {
                list.add(employee);
            }
        }
        return list;
    }
}
