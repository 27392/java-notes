package cn.haohaoli.book.core.base.chapter5.reflection;

import java.util.ArrayList;

/**
 * @author LiWenHao
 * @date 2019-01-24 11:42
 */
public class ObjectAnalyzerTest {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(i * i);
            System.out.println(new ObjectAnalyzer().toString(list));
        }
    }
}
