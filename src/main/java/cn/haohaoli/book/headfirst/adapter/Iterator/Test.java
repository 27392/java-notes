package cn.haohaoli.book.headfirst.adapter.Iterator;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author lwh
 */
public class Test {

    public static void main(String[] args) {
        Vector<Integer> integers = new Vector<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);

        loop(new EnumerationIteratorAdapter<>(integers.elements()));
    }

    public static void loop (Iterator<?> iterator) {
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
