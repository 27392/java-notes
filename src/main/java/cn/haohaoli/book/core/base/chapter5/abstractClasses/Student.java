package cn.haohaoli.book.core.base.chapter5.abstractClasses;

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

    @Override
    public String getDescription() {
        return major + "专业";
    }
}
