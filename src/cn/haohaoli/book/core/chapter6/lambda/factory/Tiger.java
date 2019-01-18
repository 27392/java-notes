package cn.haohaoli.book.core.chapter6.lambda.factory;

/**
 * @author liWenHao
 * @date 2019/1/7 11:01
 */
public class Tiger extends Animal {

    private String name;

    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
