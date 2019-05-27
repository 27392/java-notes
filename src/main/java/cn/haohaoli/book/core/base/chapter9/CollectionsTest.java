package cn.haohaoli.book.core.base.chapter9;

import java.time.LocalDate;
import java.util.*;

/**
 * @author LiWenHao
 * @date 2019-05-27 20:54
 */
public class CollectionsTest {

    public static void main(String[] args) {

        //生成一个不可修改的单元素集
        Set<LocalDate> singleton = Collections.singleton(LocalDate.now());
        List<LocalDate> localDates = Collections.singletonList(LocalDate.now());
        Map<String, String> stringStringMap = Collections.singletonMap("k", "v");

        //生成空的
        Set<Object> objects = Collections.emptySet();
        List<Object> objectList = Collections.emptyList();
        Map<Object, Object> objectObjectMap = Collections.emptyMap();

        /**
         * 返回的对象不是 ArrayList 它是一个视图对象 带有访问底层数组的 get 和 set 方 法。
         * 改变数组大小的所有方法 (例如， 与迭代器相关的 add 和 remove 方法)都会抛出一个 Unsupported OperationException 异常。
         */
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        print(integerList);

        System.out.println("=========");

        List<Integer> integerList1 = integerList.subList(0, 5);

        print(integerList1);

        System.out.println("=========");

        List<String> s = new ArrayList<String>(){{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
        }};

        s.subList(0, 4).clear();

        print(s);

        System.out.println("=========");

        s.clear();

        //同步
        List<String> strings = Collections.synchronizedList(s);


    }

    static void print (Collection<?> collection){
        collection.forEach(System.out::println);
    }
}
