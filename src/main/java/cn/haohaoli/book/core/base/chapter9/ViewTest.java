package cn.haohaoli.book.core.base.chapter9;

import java.time.LocalDate;
import java.util.*;

/**
 * @author LiWenHao
 */
public class ViewTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("a", "b", "c", "d", "e");

        List<String> aDefault = Collections.nCopies(100, "DEFAULT");

        // 单个
        Set<LocalDate>      singleton     = Collections.singleton(LocalDate.now());
        List<LocalDate>     singletonList = Collections.singletonList(LocalDate.now());
        Map<String, String> singletonMap  = Collections.singletonMap("k", "v");

        // 空
        List<Object>        emptyList = Collections.emptyList();
        Set<Object>         emptySet  = Collections.emptySet();
        Map<Object, Object> emptyMap  = Collections.emptyMap();

        // 范围视图
        subRange();

        // 不可改变视图
        unmodifiable();

        // 同步视图
        sync();

        // 受查视图
        check();
    }

    /**
     * 子视图
     */
    private static void subRange() {

        List<String> list = Arrays.asList("a", "b", "c", "d", "e");

        List<String> stringList = new ArrayList<>(list);
        List<String> subList    = stringList.subList(1, 4);
        subList.clear();                // 清除视图同时其实删除原对象内的元素, 视图中没有元素.
        System.out.println(stringList);

        SortedSet<String> treeSet = new TreeSet<>(list);

        System.out.println("subSet  : " + treeSet.subSet("a", "d"));
        System.out.println("subSet  : " + ((NavigableSet) treeSet).subSet("a", false, "d", false));
        System.out.println("headSet : " + treeSet.headSet("d"));
        System.out.println("headSet : " + ((NavigableSet) treeSet).headSet("d", false));
        System.out.println("tailSet : " + treeSet.tailSet("d"));
        System.out.println("tailSet : " + ((NavigableSet) treeSet).tailSet("d", true));

        SortedMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(1, 1);
        treeMap.put(2, 2);
        treeMap.put(3, 3);
        treeMap.put(4, 4);
        treeMap.put(5, 5);

        System.out.println("subMap    : " + treeMap.subMap(1, 3));                                       // 大于1,小于3
        System.out.println("subMap    : " + ((NavigableMap) treeMap).subMap(1, true, 3, true)); // 大于等于1,小于等于3
        System.out.println("headMap   : " + treeMap.headMap(3));                                         // 小于3
        System.out.println("headMap   : " + ((NavigableMap) treeMap).headMap(3, true));   // 小于等于3
        System.out.println("tailMap   : " + treeMap.tailMap(3));                                         // 大于等于3
        System.out.println("tailMap   : " + ((NavigableMap) treeMap).tailMap(3, false));// 大于3
    }

    /**
     * unmodifiableCollection
     * unmodifiableSet
     * unmodifiableSortedSet
     * unmodifiableNavigableSet
     * unmodifiableList
     * unmodifiableMap
     * unmodifiableSortedMap
     * unmodifiableNavigableMap
     */
    public static void unmodifiable() {
        List<String> list             = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        // unmodifiableList.add("f");  // 错误,抛出UnsupportedOperationException异常

        // 修改原集合,视图对象也会发生变动
        System.out.println(unmodifiableList);
        list.add("f");
        System.out.println(unmodifiableList);
    }

    /**
     * synchronizedCollection
     * synchronizedSet
     * synchronizedSortedSet
     * synchronizedNavigableSet
     * synchronizedList
     * synchronizedMap
     * synchronizedSortedMap
     * synchronizedNavigableMap
     */
    public static void sync() {
        Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * checkedCollection
     * checkedQueue
     * checkedSet
     * checkedSortedSet
     * checkedNavigableSet
     * checkedList
     * checkedMap
     * checkedSortedMap
     * checkedNavigableMap
     */
    public static void check() {
        LinkedList<String> list = new LinkedList<>();
        List rawList = list;
        rawList.add(LocalDate.now());
        // System.out.println(list.getFirst());    // 抛出异常 java.lang.ClassCastException

        List<String> stringList = Collections.checkedList(new LinkedList<>(), String.class);
        rawList = stringList;
        rawList.add(LocalDate.now());  //在添加时即可探测类型问题,而不是等到获取时
    }
}
