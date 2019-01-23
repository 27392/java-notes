package cn.haohaoli.book.core.chapter5.extend.abstraction;

/**
 * @author LiWenHao
 * @date 2019-01-23 21:36
 */
public class Student extends Person {

    private String major;

    public Student(String name,String major) {
        super(name);
        this.major = major;
    }

    //在 Student 类中定义了 getDescription 方法。 因此， 在 Student 类中的全部方法都是非抽 象的，这个类不再是抽象类。
    @Override
    public String getDescription() {
        return major;
    }
}
