package cn.haohaoli.book.headfirst.adapter;

/**
 * @author LiWenHao
 * @date 2019-05-27 22:07
 */
public class Test {

    public static void main(String[] args) {

        // 鸭子
        Duck duck = new MallardDuck();

        // 火鸡
        Turkey turkey = new WildTurkey();

        // 火鸡适配器
        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        System.out.println("火鸡");
        turkey.gobble();
        turkey.fly();

        System.out.println("\n鸭子");
        testDuck(duck);

        System.out.println("\n火鸡适配器");
        testDuck(turkeyAdapter);

    }

    static void testDuck(Duck duck){
        duck.quack();
        duck.fly();
    }
}
