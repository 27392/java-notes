package cn.haohaoli.book.core.base.chapter4;

import java.util.Random;

/**
 * 代码块
 * @author LiWenHao
 * @date 2019-01-23 20:07
 */
public class CodeBlockTest {

    /**
     * TODO 代码块（初始化块）
     *  资料：https://www.cnblogs.com/ysocean/p/8194428.html
     *  在一个类的声明中， 可以包含多个代码块。只要构造类的对象， 这些块就会被执行
     *  由于初始化数据域有多种途径， 所以列出构造过程的所有路径可能相当混乱。下面是调用构造器的具体处理步骤:
     *      1 ) 所有数据域被初始化为默认值(0、 false 或 null 。)
     *      2 ) 按照在类声明中出现的次序， 依次执行所有域初始化语句和初始化块。
     *      3 ) 如果构造器第一行调用了第二个构造器， 则执行第二个构造器主体
     *      4 ) 执行这个构造器的主体.
     *  在类第一次加载的时候，将会进行静态域的初始化。与实例域一样， 除非将它们显式地设置成其他值， 否则默认的初始值是 0、 false 或 null
     *  所有的静态初始化语句以及静态初始化块都将依照类定义的顺序执行。
     */

    private static Integer id;

    {
        int i = new Random().nextInt(1000);
        System.out.println("代码块1 Random=" + i);
        id = i;
    }

    {
        int i = new Random().nextInt(1000);
        System.out.println("代码块2 Random=" + i);
        id = i;
    }

    static {
        int i = new Random().nextInt(1000);
        System.out.println("static 代码块1 Random=" + i);
        id = i;
    }

    static {
        int i = new Random().nextInt(1000);
        System.out.println("static 代码块2 Random=" + i);
        id = i;
    }

    public CodeBlockTest() {
        System.out.println("构造器执行");
    }

    public static Integer getId() {
        return id;
    }

    public static void main(String[] args) {

        //首先加载 static 代码块
        System.out.println(id);
        CodeBlockTest codeBlockTest = new CodeBlockTest();
        System.out.println(id);

        /**
         * 结果
         *  static 代码块1
         *  static 代码块2
         *  代码块1
         *  代码块2
         *  构造器执行
         */
    }
}
