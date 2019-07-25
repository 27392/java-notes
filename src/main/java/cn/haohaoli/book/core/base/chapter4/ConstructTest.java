package cn.haohaoli.book.core.base.chapter4;

import java.time.LocalDate;
import java.util.Random;

/**
 * @author LiWenHao
 * @date 2019/7/25 18:34
 */
public class ConstructTest {

    public static void main(String[] args) {

        /**
         * 执行顺序:
         *  执行了静态代码块
         *  执行了assignId方法
         *  执行了代码块
         *  执行了Clerk(String,double)构造器
         *  执行完了Clerk(double)构造器
         */
        Clerk clerk = new Clerk(100);
    }
}

class Clerk {

    private static int       nextId;
    private        int       id = assignId();
    private        String    name = "";
    private        double    salary;
    private        LocalDate birthday;

    static {
        System.out.println("执行了静态代码块");
        Random generator = new Random();
        nextId = generator.nextInt(1000);
    }

    {
        System.out.println("执行了代码块");
        birthday = LocalDate.now();
    }

    public Clerk() {
        System.out.println("执行了无参构造器");
        this.salary = 0d;
    }

    public Clerk(double salary) {
        this("", salary);
        System.out.println("执行完了Clerk(double)构造器");
    }

    public Clerk(String name, double salary) {
        System.out.println("执行了Clerk(String,double)构造器");
        this.salary = salary;
        this.name = name;
    }

    private int assignId(){
        System.out.println("执行了assignId方法");
        int r = nextId;
        nextId++;
        return r;
    }
}
