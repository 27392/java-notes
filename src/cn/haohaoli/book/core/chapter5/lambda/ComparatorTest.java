package cn.haohaoli.book.core.chapter5.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

import static java.util.Comparator.*;

/**
 * @author liWenHao
 * @date 2019/1/7 21:05
 */
public class ComparatorTest {

    public static void main(String[] args) {
        Person[] people = new Person[4];
        people[0] = new Person("赵四",22);
        people[1] = new Person("张三",30);
        people[2] = new Person("李五",32);
        people[3] = new Person("王四",20);

        Arrays.sort(people, Comparator.comparing(person -> person.getName().charAt(0)));
        System.out.println(Arrays.toString(people));

        Arrays.sort(people, Comparator.comparing(Person::getName, (o1, o2) -> Integer.compare(o2.charAt(0), o1.charAt(0))));
        System.out.println(Arrays.toString(people));

        Arrays.sort(people, Comparator.comparing(Person::getName));
        System.out.println(Arrays.toString(people));

        Arrays.sort(people, Comparator.comparing(Person::getName).thenComparing(Person::getAge));
        System.out.println(Arrays.toString(people));

        Function<Person, String> getName = Person::getName;
        String apply = getName.apply(people[1]);
        System.out.println(apply);

        Person[] people1 = new Person[4];
        people1[0] = new Person("赵四",null);
        people1[1] = new Person("张三",30);
        people1[2] = new Person(null,30);
        people1[3] = new Person("李五",32);

        /**
         * https://blog.csdn.net/york_2016/article/details/80169467?utm_source=blogxgwz9
         * https://blog.csdn.net/ab411919134/article/details/80675786
         */
        //值放到前面
        Arrays.sort(people1, Comparator.comparing(Person::getName, nullsFirst(naturalOrder())));
        System.out.println("first: " + Arrays.toString(people1));
        //值放到后面
        Arrays.sort(people1, Comparator.comparing(Person::getAge, Comparator.nullsLast(Integer::compareTo)));
        System.out.println("last: " + Arrays.toString(people1));
    }
}
