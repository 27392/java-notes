package cn.haohaoli.book.core.chapter5.extend;

public class Student extends Person {

    private String major;

    public Student(String name, String major) {
        super(name);
        this.major = major;
    }

    @Override
    public String getDescription() {
        return "学生";
    }
}
