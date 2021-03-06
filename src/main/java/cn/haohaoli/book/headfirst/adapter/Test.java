package cn.haohaoli.book.headfirst.adapter;

/**
 * @author LiWenHao
 * @date 2019-05-27 22:07
 */
public class Test {

    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();

        WildTurkey turkey = new WildTurkey();

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
