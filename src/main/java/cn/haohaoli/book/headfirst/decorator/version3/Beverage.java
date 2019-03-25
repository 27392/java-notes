package cn.haohaoli.book.headfirst.decorator.version3;

/**
 * @author LiWenHao
 * @date 2019-03-12 21:18
 */
public abstract class Beverage {

    /**
     * 描述
     */
    protected String description;

    /**
     * 获取描述
     * @return  描述信息
     */
    public abstract String getDescription();

    /**
     * 计算费用
     * @return  总费用
     */
    public abstract double cost();

}
