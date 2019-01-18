package cn.haohaoli.book.core.chapter6.lambda.factory;

import java.util.function.Supplier;

/**
 * @author liWenHao
 * @date 2019/1/7 11:03
 */
public enum AnimalEnum {

    TIGER(Tiger::new),
    LION(Lion::new);

    //Supplier<T>     void     提供一个T类型的值
    private final Supplier<Animal> animal;

    AnimalEnum(Supplier<Animal> animal) {
        this.animal = animal;
    }

    public Supplier<Animal> getAnimal() {
        return animal;
    }
}
