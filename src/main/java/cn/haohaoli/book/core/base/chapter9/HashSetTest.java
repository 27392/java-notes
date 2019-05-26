package cn.haohaoli.book.core.base.chapter9;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author LiWenHao
 * @date 2019-05-26 17:26
 */
public class HashSetTest {

    public static void main(String[] args) {
        HashSet<String> words = new HashSet<>();
        InputStream is = ClassLoader.getSystemResourceAsStream("ExampleFile.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(is);

        try {
            int read;
            while ((read = inputStreamReader.read()) != -1) {
                words.add(Character.toString((char) read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //随机
        Iterator<String> iterator = words.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
