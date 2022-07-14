package cn.haohaoli.book.headfirst.iterator.version1;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 煎饼屋菜单
 *
 * @author lwh
 */
@Getter
public class PancakeHouseMenu {

    private List<MenuItem> menuItems;

    public PancakeHouseMenu(){
        this.menuItems = new ArrayList<>();
        this.addItem("K&B's薄煎饼早餐","薄煎饼、清蛋和吐司",true,2.99);
        this.addItem("薄煎饼早餐例餐","薄煎饼带煎蛋,香肠",false,2.99);
        this.addItem("蓝莓薄煎饼","新鲜草莓和蓝莓糖浆制作的薄煎饼",true,3.49);
        this.addItem("松饼","松饼,可以选择蓝莓或草莓",false,3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        menuItems.add(new MenuItem(name, description, vegetarian, price));
    }
}
