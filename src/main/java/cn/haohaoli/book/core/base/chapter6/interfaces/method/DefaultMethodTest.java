package cn.haohaoli.book.core.base.chapter6.interfaces.method;

/**
 * TODO 默认方法冲突
 *  1 ) 超类优先。 如果超类提供了一个具体方法,同名而且有相同参数类型的默认方法会被忽略
 *  2 ) 接口冲突。 如果一个超接口提供了一个默认方法,另一个接口提供了一个同名而且参数类型(不论是否是默认参数)相同的方法，必须覆盖这个方法来解决冲突
 *      //两个接口都是默认方法且同名
 *      //一个接口是默认方法，另一个接口同名
 *      //两个接口非默认方法且同名
 *      上面的种种情况都必须覆盖getName()方法
 *   资料：https://blog.csdn.net/shallowinggg/article/details/78039372
 * @author LiWenHao
 * @date 2019-02-03 15:15
 */
public class DefaultMethodTest {
}

// 两个接口都是默认方法
class Student implements Person, Named {

    @Override
    public String getName() {
        // 可以从两个默认接口中选择默认方法,也可以自己重新实现
        return Named.super.getName();
        //return Person.super.getName();
    }
}

// 一个接口时默认方法,另一个接口同名
class Teacher implements Person, Nameds {

    @Override
    public String getName() {
        // 可以选择默认接口已有的默认方法,也可以自己重新实现
        return Person.super.getName();
    }
}

// 超类有具体的方法,接口相同. 这种情况接口方法会被忽略(超类优先)
class Principal extends Base implements Nameds {

}

interface Person{

    default String getName(){
        return "Person";
    }
}

interface Named {

    default String getName(){
        return "Named";
    }
}

interface Nameds{

    String getName();
}

class Base{

    public String getName(){
        return "Base";
    }
}
