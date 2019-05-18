package cn.haohaoli.book.core.base.chapter6.lambda;

/**
 * @author liWenHao
 * @date 2019/1/5 17:08
 */
public class Person {

    private String name;

    private Integer age;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(Integer age) {
        this.age = age;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void print() {
        System.out.println("Person");
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
