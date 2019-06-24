package cn.haohaoli.data.structure.array;

import java.util.Arrays;

/**
 * @author LiWenHao
 * @date 2019/6/19 17:49
 */
public class Main {

    public static void main(String[] args) {
        /*int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

//        int[] scores = new int[]{100, 99, 66};
        //简写
        int[] scores = {100, 99, 66};
        System.out.println(Arrays.toString(scores));
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }*/

        Array array = new Array(20);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);
        array.add(1,100);
        System.out.println(array);
        array.remove(10);
        System.out.println(array);

    }
}

