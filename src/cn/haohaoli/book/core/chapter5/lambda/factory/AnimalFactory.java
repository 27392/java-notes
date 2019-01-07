package cn.haohaoli.book.core.chapter5.lambda.factory;

/**
 * 动物工厂
 * @author liWenHao
 * @date 2019/1/7 11:05
 */
public class AnimalFactory {

    public static Animal create(AnimalEnum anEnum){
        return anEnum.getAnimal().get();
    }
}
