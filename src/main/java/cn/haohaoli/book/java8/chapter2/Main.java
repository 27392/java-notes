package cn.haohaoli.book.java8.chapter2;

import cn.haohaoli.book.java8.chapter2.lambda.Predicate;
import cn.haohaoli.book.java8.chapter2.strategy.ApplePredicate;
import cn.haohaoli.book.java8.chapter2.strategy.impl.AppleGreenColorPredicate;
import cn.haohaoli.book.java8.chapter2.strategy.impl.AppleHeavyWeightPredicate;
import cn.haohaoli.book.java8.chapter2.strategy.impl.AppleRedAndHeavyPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiWenHao
 */
public class Main {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple("green", 100),
                new Apple("yellow", 160),
                new Apple("red", 155)
        );

        // 第一次; 筛选绿色的苹果
        System.out.println(filterGreenApples(inventory));

        // 第二次; 不再筛选单一的颜色,将颜色进行传递.同理完成重量的筛选
        System.out.println(filterApplesByColor(inventory, "green"));
        System.out.println(filterApplesByColor(inventory, "yellow"));
        System.out.println(filterApplesByWeight(inventory, 150));

        // 第三次; 优化(颜色和重量的,这两个方法中大部分都是相同的)将筛选颜色和重量的方法整合为一个方法
        System.out.println(filterApples(inventory, "red", 0, true));
        System.out.println(filterApples(inventory, "", 155, false));

        // 第四次; 为了适应更多的变化将不同的行为参数化,封装为一个个的`策略`,然后在运行时选择一个策略
        System.out.println(filterApples(inventory, new AppleGreenColorPredicate()));
        System.out.println(filterApples(inventory, new AppleHeavyWeightPredicate()));
        System.out.println(filterApples(inventory, new AppleRedAndHeavyPredicate()));

        // 第五次; 使用匿名类优化策略(每当有新的行为时,都需要新的类,如果行为过多就会产生类爆炸💥)
        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
                    public boolean test(Apple a) {
                        return "red".equals(a.getColor());
                    }
                }
        );
        System.out.println(redApples);

        // 第六次; 使用lambda表达式去优化匿名类
        System.out.println(filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor())));

        // 第七次; 进一步的抽象,利用泛型替代原有`Apple`类.从而使得方法更加的通用,而不仅仅局限于`Apple`类
        System.out.println(filter(inventory, (Apple apple) -> "red".equals(apple.getColor())));
        System.out.println(filter(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> i % 2 == 0));
    }


    /**
     * 筛选绿色的苹果
     *
     * @param inventory 库存
     * @return 筛选后的列表
     */
    private static List<Apple> filterGreenApples(List<Apple> inventory) {
        ArrayList<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 筛选库存根据颜色
     *
     * @param inventory 库存
     * @param color     颜色
     * @return 筛选后的列表
     * @see #filterGreenApples(List) 升级版
     */
    private static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        ArrayList<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 筛选库存根据重量
     *
     * @param inventory 库存
     * @param weight    重量
     * @return 筛选后的列表
     */
    private static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        ArrayList<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 筛选库存根据颜色或者是重量
     *
     * @param inventory 库存
     * @param color     颜色
     * @param weight    重量
     * @param flag      true,颜色/false,重量
     * @return 筛选后的列表
     * @see #filterApplesByColor(List, String)
     * @see #filterApplesByWeight(List, int)
     * 这两个方法的升级版
     */
    private static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color))
                    || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 筛选库存根据ApplePredicate接口
     *
     * @param inventory 库存
     * @param predicate 条件接口
     * @return 筛选后的列表
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 泛型化过滤条件
     *
     * @param list      列表
     * @param predicate 条件
     * @param <T>       元素类型
     * @return 筛选后的列表
     */
    private static <T> List<T> filter(List<? extends T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for (T e : list) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
