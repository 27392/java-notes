package cn.haohaoli.book.headfirst.template.version3;

/**
 * @author LiWenHao
 * @date 2019-05-05 21:29
 */
public class Test {

    public static void main(String[] args) {
        CaffeineBeverage tea = new Tea();
        tea.prepareRecipe();

        System.out.println();

        CaffeineBeverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
