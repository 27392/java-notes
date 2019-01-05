package cn.haohaoli.book.core.chapter5.reflection;

import java.util.ArrayList;

public class ObjectAnalyzerTest {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(i * i);
            System.out.println(new ObjectAnalyzer().toString(list));
        }
    }
}
