package cn.haohaoli.book.core.base.chapter7.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

/**
 * TODO 堆栈轨迹是一个方法调用过程的列表,它包含了程序执行过程中方法调用的特定位置
 *  可以使用Throwable类的printStackTrace方法访问堆栈轨迹的文本描述信息
 *  还可以使用getStackTrace方法,它会得到StackTraceElement对象的一个数组
 *      StackTraceElement类含有能够获得文件名和当前执行的代码行号的方法,
 *      同时,还含有能够获得类名和方法名的方法.
 * @author LiWenHao
 * @date 2019/10/10 14:25
 */
public class StackTraceTest {

    public static void main(String[] args) {

        Throwable    throwable = new Throwable();
        StringWriter out       = new StringWriter();
        throwable.printStackTrace(new PrintWriter(out));
        System.out.println(out.toString());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        factorial(scanner.nextInt());
    }

    /**
     * 打印阶乘的堆栈信息
     * @param n
     * @return
     */
    private static int factorial(int n) {
        System.out.println("factorial: " + n);
        Throwable           t        = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();
        for (StackTraceElement stackTraceElement : elements) {
            System.out.println(stackTraceElement);
        }
        int r;
        if (n <= 1) {
            r = 1;
        } else {
            r = n * factorial(n - 1);
        }
        System.out.println("return: " + r);
        return r;
    }
}
