package cn.haohaoli.book.core.chapter6.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

/**
 * TODO Comparator 接口 （java8）
 *  Comparator 接口包含很多方便的静态方法来创建比较器。 这些方法可以用于 lambda 表 达式或方法引用。
 *  静态 comparing 方法取一个“ 键提取器” 函数， 它将类型 T 映射为一个可比较的类型 (如 String)。 对要比较的对象应用这个函数， 然后对返回的键完成比较。
 * @author liWenHao
 * @date 2019/1/7 21:05
 */
public class ComparatorTest {

    public static void main(String[] args) {
        Person[] people = new Person[5];
        people[0] = new Person("赵四",22);
        people[1] = new Person("张三",30);
        people[2] = new Person("李五",32);
        people[3] = new Person("王四",20);
        people[4] = new Person("赵四",12);

        //按名字排序
        Arrays.sort(people, Comparator.comparing(Person::getName));
        System.out.println(Arrays.toString(people));

        //thenComparing 可以吧比较器串联起来。如果两个人的姓相同， 就会使用第二个比较器。
        Arrays.sort(people, Comparator.comparing(Person::getName).thenComparing(Person::getAge));
        System.out.println(Arrays.toString(people));

        //键提取器
        Function<Person, String> getName = Person::getName;
        String apply = getName.apply(people[1]);
        System.out.println(apply);

        Person[] people1 = new Person[4];
        people1[0] = new Person("赵四",null);
        people1[1] = new Person("张三",30);
        people1[2] = new Person(null,30);
        people1[3] = new Person("李五",32);

        /**
         * TODO 比较null值比较
         *  资料 ：
         *      https://blog.csdn.net/york_2016/article/details/80169467?utm_source=blogxgwz9
         *      https://blog.csdn.net/ab411919134/article/details/80675786
         *  如果键函数可以返回null, 可能就要用到nullsFirst和nullsLast适配器。这些静态方法会修改现有的比较器，
         *  从而在遇到 null 值时不会抛出异常， 而是将这个值标记为小于或 大于正常值。
         *  例如，假设一个人没有中名时getName会返回一个null,就可以使用
         *      Comparator.comparing(Person::getName, Comparator.nullsFirst(Comparator.naturalOrder());
         *  nullsFirst 方法需要一个比较器， 在这里就是比较两个字符串的比较器。
         *      naturalOrder 方法可以为任何实现了 Comparable 的类建立一个自然比较器。
         *      reverseOrder 方法可以为任何实现了 Comparable 的类建立一个反向比较器。
         *  在这里 naturalOrder 正是我们需要的
         */
        //null值放到前面
        Arrays.sort(people1, Comparator.comparing(Person::getName, Comparator.nullsFirst(Comparator.naturalOrder())));
        System.out.println("getName nullsFirst: " + Arrays.toString(people1));
        Arrays.sort(people1, Comparator.comparing(Person::getName, Comparator.nullsLast(Comparator.naturalOrder())));
        System.out.println("getName nullsLast: " + Arrays.toString(people1));
        //null值放到后面
        Arrays.sort(people1, Comparator.comparing(Person::getAge, Comparator.nullsLast(Comparator.naturalOrder())));
        System.out.println("getAge nullsLast: " + Arrays.toString(people1));
        Arrays.sort(people1, Comparator.comparing(Person::getAge, Comparator.nullsLast(Comparator.reverseOrder())));
        System.out.println("getAge nullsLast reverseOrder : " + Arrays.toString(people1));
    }
}
