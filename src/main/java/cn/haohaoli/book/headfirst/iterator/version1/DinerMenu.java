package cn.haohaoli.book.headfirst.iterator.version1;

import lombok.Getter;

/**
 * @author lwh
 */
@Getter
public class DinerMenu {

    private static final int MAX_ITEMS = 6;

    private int        numberOfItems = 0;
    private MenuItem[] menuItems;

    public DinerMenu() {
        this.menuItems = new MenuItem[MAX_ITEMS];
        this.addItem("素食BLT", "(煎)培根、生菜和番茄使用全麦面包制作", true, 2.99);
        this.addItem("BLT", "培根、生菜&西红柿", false, 2.99);
        this.addItem("例汤", "一碗例汤,配土豆沙拉", false, 3.29);
        this.addItem("热狗", "热狗,酸菜,上盖芝士", false, 3.05);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        if (numberOfItems >= MAX_ITEMS) {
            throw new RuntimeException("抱歉,菜单已经满了.不能再继续添加");
        }
        menuItems[numberOfItems++] = new MenuItem(name, description, vegetarian, price);
    }
}
