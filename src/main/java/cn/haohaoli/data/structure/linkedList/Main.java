package cn.haohaoli.data.structure.linkedList;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author LiWenHao
 * @date 2019-06-27 22:53
 */
public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            linkedList.addLast(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);

        System.out.println(linkedList);

        System.out.println("contains: " + linkedList.contains(666));

        linkedList.remove(2);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);

        System.out.println(new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        System.out.println(new LinkedList<>(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)));

    }
}
