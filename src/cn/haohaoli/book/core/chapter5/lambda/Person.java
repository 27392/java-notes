package cn.haohaoli.book.core.chapter5.lambda;

/**
 * @author liWenHao
 * @date 2019/1/5 17:08
 **/
public class Person {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println("Person");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
