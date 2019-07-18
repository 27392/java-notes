package cn.haohaoli.data.structure.bst;

import java.util.Arrays;

/**
 * @author LiWenHao
 * @date 2019/7/11 16:20
 */
public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>(Arrays.asList(5, 3, 6, 8, 4, 2, 1, 7));

        /**
         *          5
         *        /  \
         *       3    6
         *     /  \    \
         *    2   4     8
         *   /         /
         *  1         7
         */
        System.out.println("===========preOrder===========");
        bst.preOrder();
        System.out.println("===========inOrder===========");
        bst.inOrder();
        System.out.println("===========postOrder===========");
        bst.postOrder();
        System.out.println("===========levelOrder===========");
        bst.levelOrder();
        System.out.println("===========preOrderNR===========");
        bst.preOrderNR();
//        System.out.println(bst);
        System.out.println("===========min max===========");
        System.out.println(bst.minimum());
        System.out.println(bst.minimumNR());
        System.out.println(bst.maximum());
        System.out.println(bst.maximumNR());
        System.out.println("removeMin");
        System.out.println(bst.removeMin());
        System.out.println(bst.removeMax());
        System.out.println("+++++++++++++++++++++++++++++++++++");
        bst.preOrderNR();
    }
}
