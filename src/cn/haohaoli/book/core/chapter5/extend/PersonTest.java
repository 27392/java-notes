package cn.haohaoli.book.core.chapter5.extend;

public class PersonTest {

    public static void main(String[] args) {
        Person[] peoples = new Person[2];
        peoples[0] = new Employee("e", 5000, 2018, 10, 1);
        peoples[1] = new Student("s","c");

        for (Person p : peoples) {
            System.out.println(p.getName() + " " + p.getDescription());
        }
    }
}
