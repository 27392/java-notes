package cn.haohaoli.book.core.base.chapter9;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LiWenHao
 * Collections视图相关内容在: {@link ViewTest}
 */
public class CollectionsTest {

    public static void main(String[] args) {

        // 二分查找
        binarySearch();

        // 排序
        sort();

        // 随机地打乱列表中的元素
        shuffle();

        // 将列表中所有位置设置为相同的值
        fill();

        // 原列表中的所有元素复制到目辱列表的相应位置上
        copy();

        // 将所有的值添加到集合中
        addAll();

        // 返回集合中最小的或最大的元素
        minmax();

        // 逆置列表中元素的顺序
        reverse();

        // 交换给定偏移量的两个元素
        swap();

        // 判断两个集合是否有不相同的元素
        disjoint();

        // 获取列表中指定元素相同的个数
        frequency();

        // 返回子集在列表中出现的索引第一次、最后一次出现的位置
        indexOfSubList();

        // 旋转列表中的元素
        rotate();

        // newSetFromMap
        Set<String> concurrentHashSet = Collections.newSetFromMap(new ConcurrentHashMap<>());

    }

    /**
     * 旋转列表中的元素,将索引`i`的条目移动到位置`(i + distance)) % list.size()`
     * rotate(List<?> list, int distance)
     *  例如:
     *    将列表 [t a,r] 旋转移 2 个位置后得到 [a,r,t]
     * 这个方法的时间复杂度为 O(n), n 为列表的长度
     * 这个目前不是很理解,以后有机会再研究
     */
    private static void rotate() {
        System.out.println("========rotate=========");
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collections.rotate(list, 3);
        System.out.println(list);
    }

    /**
     * 返回子集在列表中出现的索引第一次(indexOfSubList)、最后一次(lastIndexOfSubList)出现的位置
     *  Collections.indexOfSubList(资源, 目标)
     *  Collections.lastIndexOfSubList(资源, 目标)
     *  例如:
     *    A[1,2,3],B[1,2,3,4,1,2,3]
     *    Collections.indexOfSubList(A, B)        // 返回: `0`
     *    Collections.lastIndexOfSubList(A, B)    // 返回: `5`
     */
    private static void indexOfSubList() {
        System.out.println("========indexOfSubList=========");
        ArrayList<Integer> listA = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 1, 2, 3, 6, 1, 2, 3));
        ArrayList<Integer> listB = new ArrayList<>(Arrays.asList(1, 2, 3));

        System.out.println("indexOfSubList: " + Collections.indexOfSubList(listA, listB));
        System.out.println("lastIndexOfSubList" + Collections.lastIndexOfSubList(listA, listB));
    }

    /**
     * 获取列表中指定元素相同的个数
     *  例如:
     *   A[1,2,3]
     *   Collections.frequency(A, 1) // 返回:1
     *   Collections.frequency(A, 5) // 返回:0
     */
    private static void frequency() {
        System.out.println("========frequency=========");
        List<Integer> list = new ArrayList<>(Arrays.asList(13, 5, 2, 4, 1, 5, 66, 1));
        System.out.println(Collections.frequency(list, 1)); // list中与`1`相同的个数
        System.out.println(Collections.frequency(list, 6)); // list中与`6`相同的个数
    }

    /**
     * 判断两个集合是否有不相同的元素.如果两个集合没有共同的元素,则返回`true`
     */
    private static void disjoint() {
        System.out.println("========disjoint=========");
        List<Integer> listA = new ArrayList<>(Arrays.asList(13, 5, 2, 4, 5, 66, 1));
        List<Integer> listB = new ArrayList<>(Arrays.asList(13, 2));
        List<Integer> listC = new ArrayList<>(Arrays.asList(99, 100));

        System.out.println(Collections.disjoint(listA, listB)); // false
        System.out.println(Collections.disjoint(listB, listC)); // true
    }

    /**
     * 交换给定偏移量的两个元素
     *  例如:
     *   A[1,2,3]
     *   Collections.swap(A, 0, A.size() - 1); // [3,2,1]
     *   B[1,2,3]
     *   Collections.swap(B, 0, 1);            // [2,1,3]
     */
    private static void swap() {
        System.out.println("========swap=========");
        List<Integer> integers = new ArrayList<>(Arrays.asList(13, 5, 2, 4, 5, 66, 1));
        Collections.swap(integers, 0, integers.size() - 1); // 交换第一个元素和最后一个元素
        System.out.println(integers);   // [1, 66, 5, 4, 2, 5, 13]
    }

    /**
     * 逆置列表中元素的顺序
     */
    private static void reverse() {
        System.out.println("========reverse=========");
        List<Integer> integers = new ArrayList<>(Arrays.asList(13, 5, 2, 4, 5, 66, 1));
        Collections.reverse(integers);
        System.out.println(integers);   //[1, 5, 2, 4, 5, 66, 13]
    }

    /**
     * 返回集合中最小的或最大的元素
     *  元素如果没有实现`Comparable`接口那就必须提供`Comparator`
     */
    private static void minmax() {
        System.out.println("========minmax=========");
        List<Integer> integers = new ArrayList<>(Arrays.asList(13, 5, 2, 4, 5, 66, 1));

        Integer min = Collections.min(integers);
        Integer max = Collections.max(integers);

        System.out.println("min: " + min + " ,max: " + max);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add((Employee.of("li", 8000d, LocalDate.of(1993, 2, 1))));
        employeeList.add((Employee.of("zhang", 9500d, LocalDate.of(1997, 7, 24))));
        employeeList.add((Employee.of("chen", 10000d, LocalDate.of(1997, 7, 4))));
        employeeList.add((Employee.of("wang", 5000d, LocalDate.of(1996, 6, 6))));

        Employee minEmployee = Collections.min(employeeList, Comparator.comparing(Employee::getSalary));
        Employee maxEmployee = Collections.max(employeeList, Comparator.comparing(Employee::getSalary));

        System.out.println("minEmployee: " + minEmployee + " ,maxEmployee: " + maxEmployee);

    }

    /**
     * 将所有的值添加到集合中.如果集合改变了返回`true`(可以理解为批量追加元素)
     *  源码中有个`|=`有空学习下
     */
    private static void addAll() {
        System.out.println("========addAll=========");
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        boolean    result  = Collections.addAll(list, 4, 5, 6);
        System.out.println("result : " + result + " ,list : " + list.toString());
    }

    /**
     * 原列表中的所有元素复制到目辱列表的相应位置上.目标列表的长度至少与原列表一样
     *  Collections.copy(目标, 来源);
     *  例如:
     *   A[1,2,3],B[12,13,14,15]
     *   Collections.copy(B, A);
     *   那么结果B的结果将会是[1,2,3,15]
     *  注意:
     *   目标长度不能小于来源长度
     */
    private static void copy() {
        System.out.println("========copy=========");
        List<Integer> listA = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> listB = new ArrayList<>(Arrays.asList(2, 3, 4, 21, 34, 5, 1234, 2346, 11, 3, 4));
        Collections.copy(listB, listA); // 将 listA 复制到 listB 中,超过长度不做替换
        System.out.println(listB);      // [1, 2, 3, 4, 5, 6, 7, 8, 11, 3, 4]
    }

    /**
     * 将列表中所有位置设置为相同的值
     *  错误示范:
     *     List<Integer> list1 = new ArrayList<>(10);
     *     Collections.fill(list1, 0);
     *     // new ArrayList<>(10) 这样只是指定容量(capacity),并不是初始化数组的长度,底层长度还是0
     */
    private static void fill() {
        System.out.println("========fill=========");
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Collections.fill(list, 0);  // 将所有位置都设置为`0`
        System.out.println(list);
    }

    /**
     * 排序
     */
    private static void sort() {
        System.out.println("========sort=========");
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

    /**
     * 随机地打乱列表中的元素
     */
    private static void shuffle() {
        System.out.println("========shuffle=========");
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
        System.out.println("========二分查找=========");
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
