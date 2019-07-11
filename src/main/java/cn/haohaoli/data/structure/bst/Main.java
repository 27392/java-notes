package cn.haohaoli.data.structure.bst;

import java.util.Arrays;

/**
 * @author LiWenHao
 * @date 2019/7/11 16:20
 */
public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>(Arrays.asList(5, 3, 6, 8, 4, 2));

        /**
         *        5
         *      /  \
         *     3    6
         *   /  \    \
         *  2   4     8
         */
        bst.preOrder();
        System.out.println("=======================");
        System.out.println(bst);
    }
}
