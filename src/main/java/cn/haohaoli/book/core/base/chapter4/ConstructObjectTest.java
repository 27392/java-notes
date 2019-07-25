package cn.haohaoli.book.core.base.chapter4;

import java.time.LocalDate;

/**
 * @author LiWenHao
 * @date 2019/7/25 18:34
 */
public class ConstructObjectTest {

    public static void main(String[] args) {
        new Clerk();
    }
}

class Clerk {

    private static int       nextId;
    private        int       id = assignId();
    private        String    name = "";
    private        double    salary;
    private        LocalDate birthday;

    {
        birthday = LocalDate.now();
    }

    public Clerk() {
        this.salary = 0d;
    }

    public Clerk(double salary) {
        this("", salary);
    }

    public Clerk(String name, double salary) {
        this.salary = salary;
        this.name = name;
    }

    private int assignId(){
        int r = nextId;
        nextId++;
        return r;
    }
}
