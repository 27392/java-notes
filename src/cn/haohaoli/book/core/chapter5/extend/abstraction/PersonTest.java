package cn.haohaoli.book.core.chapter5.extend.abstraction;

/**
 * 人测试类
 * @author LiWenHao
 * @date 2019-01-23 21:42
 */
public class PersonTest {

    public static void main(String[] args) {
        Person[] persons = new Person[2];
        persons[0] = new Employee("小张", 50000, 1989, 10, 1);
        persons[1] = new Student("小明", "高二学生");

        for (Person person : persons) {
            System.out.println(person.getName() + "," + person.getDescription());
        }
    }
}
