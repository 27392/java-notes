package cn.haohaoli.book.core.base.chapter4;

/**
 * @author LiWenHao
 * @date 2019-07-24 21:09
 */
public class StaticTest {

    public static void main(String[] args) {
        Staff[] staffs = new Staff[3];
        staffs[0] = new Staff("张三",10000d);
        staffs[1] = new Staff("王五",10300d);
        staffs[2] = new Staff("赵四",4050d);

        for (Staff staff : staffs) {
            staff.setId();
            System.out.println("id=" + staff.getId() + ",name=" + staff.getName() + ",salary=" + staff.getSalary());
        }
        System.out.println("nextId = "+Staff.getNextId());
    }
}

class Staff {

    private static int nextId = 1;
    private final String name;
    private double salary;
    private int id;

    public Staff(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = nextId;
        nextId++;
    }
}