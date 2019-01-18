package cn.haohaoli.book.core.chapter6.lambda;

import cn.haohaoli.book.core.chapter6.lambda.factory.Animal;
import cn.haohaoli.book.core.chapter6.lambda.factory.AnimalEnum;
import cn.haohaoli.book.core.chapter6.lambda.factory.AnimalFactory;

/**
 * @author liWenHao
 * @date 2019/1/7 10:22
 */
public class SimpleFactorySupplierTest {

    /**
     *  简单工厂模式
     *  参考-> https://www.cnblogs.com/invoker-/p/7684913.html
     */
    public static void main(String[] args) {

        Animal animal = AnimalFactory.create(AnimalEnum.TIGER);
        Animal animal1 = AnimalFactory.create(AnimalEnum.LION);
    }
}
