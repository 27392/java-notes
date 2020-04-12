package cn.haohaoli.book.core.base.chapter9;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

/**
 * @author LiWenHao
 */
public class CollectionsTest {

    public static void main(String[] args) {

        System.out.println("========二分查找=========");
        binarySearch();
        System.out.println("========sort=========");
        sort();
        System.out.println("========shuffle=========");
        shuffle();

        /*
        Collections.
        sort
        sort
        binarySearch
        binarySearch
        reverse
        shuffle
        shuffle
        swap
        fill
        copy
        min
        min
        max
        max
        rotate
        replaceAll
        indexOfSubList
        lastIndexOfSubList
        unmodifiableCollection
        unmodifiableSet
        unmodifiableSortedSet
        unmodifiableNavigableSet
        unmodifiableList
        unmodifiableMap
        unmodifiableSortedMap
        unmodifiableNavigableMap
        synchronizedCollection
        synchronizedSet
        synchronizedSortedSet
        synchronizedNavigableSet
        synchronizedList
        synchronizedMap
        synchronizedSortedMap
        synchronizedNavigableMap
        checkedCollection
        checkedQueue
        checkedSet
        checkedSortedSet
        checkedNavigableSet
        checkedList
        checkedMap
        checkedSortedMap
        checkedNavigableMap
        emptyIterator
        emptyListIterator
        emptyEnumeration
        emptySet
        emptySortedSet
        emptyNavigableSet
        emptyList
        emptyMap
        emptySortedMap
        emptyNavigableMap
        singleton
        singletonList
        singletonMap
        nCopies
        reverseOrder
        reverseOrder
        enumeration
        list
        frequency
        disjoint
        addAll
        newSetFromMap
        asLifoQueue
         */
        //生成空的
       /* Set<Object>         objects         = Collections.emptySet();
        List<Object>        objectList      = Collections.emptyList();
        Map<Object, Object> objectObjectMap = Collections.emptyMap();

        *//**
         * 返回的对象不是 ArrayList 它是一个视图对象 带有访问底层数组的 get 和 set 方 法。
         * 改变数组大小的所有方法 (例如， 与迭代器相关的 add 和 remove 方法)都会抛出一个 Unsupported OperationException 异常。
         *//*
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        print(integerList);

        System.out.println("=========");

        List<Integer> integerList1 = integerList.subList(0, 5);

        print(integerList1);

        System.out.println("=========");

        List<String> s = new ArrayList<String>() {{
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
        List<String> strings = Collections.synchronizedList(s);*/


    }

    private static void sort() {
        List<Employee> linkedList = new LinkedList<>();
        linkedList.add((Employee.of("li", 10000d, LocalDate.of(1996, 2, 1))));
        linkedList.add((Employee.of("zhang", 9500d, LocalDate.of(1995, 1, 1))));
        linkedList.add((Employee.of("chen", 10000d, LocalDate.of(1996, 6, 4))));
        linkedList.add((Employee.of("wang", 5000d, LocalDate.of(1996, 6, 6))));

        // 根据Comparable来排序
        Collections.sort(linkedList);
        System.out.println(linkedList);

        // 根据工资自然排序
        linkedList.sort(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(linkedList);

        // 倒序排序
        linkedList.sort(Comparator.reverseOrder());
        System.out.println(linkedList);

        // 根据生日倒序排序
        linkedList.sort(Comparator.comparing(Employee::getBirthday).reversed());
        System.out.println(linkedList);
    }

    private static void shuffle() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 4, 52, 5234, 12, 6, 12233, 6666, 33, 2223));

        Collections.shuffle(list);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println(list);
    }

    /**
     * 二分查找
     * 注意: 二分查找集合必须是`List`接口的子类,并且是排好序(自然排序). 如果使用倒序的话数据会有问题,具体看下面实例
     */
    private static void binarySearch() {
        List<Integer> arrayList = new ArrayList<>(Arrays.asList(4, 5677, 888, 33123, 44, 32, 45, 1, 4666));

        System.out.println("naturalOrder");
        arrayList.sort(Comparator.naturalOrder());
        System.out.println("naturalOrder : " + arrayList);
        int i = Collections.binarySearch(arrayList, 55);
        System.out.println("search not exist : " + i);
        System.out.println("search exist : " + Collections.binarySearch(arrayList, 4));

        // 如果元素不存在
        if (i < 0) {
            ArrayList<Integer> list = new ArrayList<>(arrayList);
            list.add(-i - 1, 55);
            System.out.println("insert after : " + list);
        }

        System.out.println("reverseOrder");
        arrayList.sort(Comparator.reverseOrder());
        System.out.println("reverseOrder : " + arrayList);
        i = Collections.binarySearch(arrayList, 55);
        System.out.println("search not exist : " + i);
        System.out.println("search exist :  " + Collections.binarySearch(arrayList, 4));

        // 如果元素不存在
        if (i < 0) {
            ArrayList<Integer> list = new ArrayList<>(arrayList);
            list.add(-i - 1, 55);
            System.out.println("insert after : " + list);
        }
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    private static class Employee implements Comparable<Employee> {

        private String    name;
        private Double    salary;
        private LocalDate birthday;

        @Override
        public int compareTo(Employee o) {
            return name.compareTo(o.name);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", "[", "]")
                    .add(name)
                    .add(salary.toString())
                    .add(birthday.toString())
                    .toString();
        }
    }
}
