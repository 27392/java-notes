package cn.haohaoli.book.core.base.chapter5.enums;

/**
 * TODO 枚举类
 *  所有的枚举类型都是 Enum 类的子类。
 * @author LiWenHao
 * @date 2019/1/24 11:20
 */
public enum Size {

    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL");

    private String abbreviation;

    private Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
