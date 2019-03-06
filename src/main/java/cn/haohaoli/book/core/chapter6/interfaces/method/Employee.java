package cn.haohaoli.book.core.chapter6.interfaces.method;

/**
 * TODO 默认方法冲突
 *  1 ) 超类优先。 如果超类提供了一个具体方法， 同名而且有相同参数类型的默认方法会 被忽略。
 *      public class Employee extends Base implements Person
 *       输出 Base
 *  2 ) 接口冲突。 如果一个超接口提供了一个默认方法， 另一个接口提供了一个同名而且 参数类型(不论是否是默认参数)相同的方法，必须覆盖这个方法来解决冲突。
 *      //一个接口是默认方法，另一个接口同名
 *      public class Employee implements Persons, Named
 *      //两个接口都是默认方法且同名
 *      public class Employee implements Persons, Nameds
 *      //两个接口非默认方法且同名
 *      public class Employee implements Person, Named
 *      上面的种种情况都必须覆盖getName()方法
 *   资料：https://blog.csdn.net/shallowinggg/article/details/78039372
 *
 * @author LiWenHao
 * @date 2019-02-03 15:15
 */
public class Employee extends Base implements Person, Named {

    public static void main(String[] args) {
        Employee employee = new Employee();
        System.out.println(employee.getName());
    }
}

interface Person{

    String getName();
}

interface Persons{

    default String getName(){
        return "Persons";
    }
}

interface Named{

    String getName();
}

interface Nameds{

    default String getName(){
        return "Nameds";
    };
}

class Base{

    public String getName(){
        return "Base";
    }
}
