package cn.haohaoli.book.headfirst.composite.version2;

import cn.haohaoli.book.headfirst.composite.version2.component.Menu;
import cn.haohaoli.book.headfirst.composite.version2.component.MenuComponent;
import cn.haohaoli.book.headfirst.composite.version2.component.MenuItem;

/**
 * @author lwh
 */
public class Test {

    public static void main(String[] args) {
        MenuComponent pancakeHouseMenu = new Menu("煎饼屋菜单", "早餐");
        MenuComponent dinerMenu        = new Menu("快餐店菜单", "午餐");
        MenuComponent cafeMenu         = new Menu("咖啡店菜单", "晚餐");
        MenuComponent dessertMenu      = new Menu("甜点店菜单", "甜点的诅咒!");

        MenuComponent allMenu = new Menu("全部菜单", "全部菜单的组合");
        allMenu.add(pancakeHouseMenu);
        allMenu.add(dinerMenu);
        allMenu.add(cafeMenu);
        allMenu.add(dessertMenu);

        // 菜单项
        pancakeHouseMenu.add(new MenuItem("K&B's薄煎饼早餐", "薄煎饼、清蛋和吐司", true, 2.99));
        pancakeHouseMenu.add(new MenuItem("薄煎饼早餐例餐", "薄煎饼带煎蛋,香肠", false, 2.99));
        pancakeHouseMenu.add(new MenuItem("蓝莓薄煎饼", "新鲜草莓和蓝莓糖浆制作的薄煎饼", true, 3.49));
        pancakeHouseMenu.add(new MenuItem("松饼", "松饼,可以选择蓝莓或草莓", false, 3.59));
        dinerMenu.add(new MenuItem("素食BLT", "(煎)培根、生菜和番茄使用全麦面包制作", true, 2.99));
        dinerMenu.add(new MenuItem("BLT", "培根、生菜&西红柿", false, 2.99));
        dinerMenu.add(new MenuItem("例汤", "一碗例汤,配土豆沙拉", false, 3.29));
        dinerMenu.add(new MenuItem("热狗", "热狗,酸菜,上盖芝士", false, 3.05));
        cafeMenu.add(new MenuItem("黑糖玛奇朵", "黑糖玛奇朵", true, 3.99));
        cafeMenu.add(new MenuItem("冰美式", "冰美式", false, 3.69));
        cafeMenu.add(new MenuItem("黑咖啡", "黑咖啡", true, 4.29));
        dessertMenu.add(new MenuItem("泡芙", "泡芙", false, 1.29));
        dessertMenu.add(new MenuItem("菠萝包", "菠萝包", false, 2.00));

        // 服务员
        Waitress waitress = new Waitress(allMenu);
        waitress.printVegetarianMenu();

    }
}
