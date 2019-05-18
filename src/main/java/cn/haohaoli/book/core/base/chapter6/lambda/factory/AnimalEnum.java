package cn.haohaoli.book.core.base.chapter6.lambda.factory;

import java.util.function.Supplier;

/**
 * TODO Supplier 接口
 *    Supplier<T>  参数 T 返回 T
 *    方法：
 *     主要方法：
 *       T get() 获取提供的对象实例
 * 动物枚举
 * @author liWenHao
 * @date 2019/1/7 11:03
 */
public enum AnimalEnum {

    TIGER(Tiger::new),
    LION(Lion::new);

    private final Supplier<Animal> animal;

    AnimalEnum(Supplier<Animal> animal) {
        this.animal = animal;
    }

    public Supplier<Animal> getAnimal() {
        return animal;
    }
}
