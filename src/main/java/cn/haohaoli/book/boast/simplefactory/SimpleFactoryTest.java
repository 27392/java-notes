package cn.haohaoli.book.boast.simplefactory;

import java.util.Scanner;

/**
 * @author LiWenHao
 * @date 2019/2/25 11:37
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        System.out.println(
                "   ____           _                  _           _                         \n" +
                "  / ___|   __ _  | |   ___   _   _  | |   __ _  | |_    ___    _ __        \n" +
                " | |      / _` | | |  / __| | | | | | |  / _` | | __|  / _ \\  | '__|      \n" +
                " | |___  | (_| | | | | (__  | |_| | | | | (_| | | |_  | (_) | | |          \n" +
                "  \\____|  \\__,_| |_|  \\___|  \\__,_| |_|  \\__,_|  \\__|  \\___/  |_|   \n" +
                "                                                  简单工厂实现计算器\n ");
        System.out.println("请输入第一个数");
        String a = new Scanner(System.in).next();
        System.out.println("请输入运算符");
        String o = new Scanner(System.in).next();
        System.out.println("请输入第二个数");
        String b = new Scanner(System.in).next();
        Algorithm algorithm = AlgorithmFactory.createAlgorithm(o);
        algorithm.setA(a);
        algorithm.setB(b);
        String result = algorithm.getResult();
        System.out.printf("结果为：%s \n", result);
    }
}
