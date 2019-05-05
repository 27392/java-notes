package cn.haohaoli.book.headfirst.singleton;

/**
 * @author LiWenHao
 * @date 2019-05-05 18:07
 */
public class ChocolateBoiler {

    private boolean empty;
    private boolean boiled;

    private volatile static ChocolateBoiler chocolateBoiler;

    private ChocolateBoiler(){
        empty = true;
        boiled = false;
    }

    /**
     * 双重检查
     * @return
     */
    public static ChocolateBoiler getInstance() {
        if (chocolateBoiler == null) {
            synchronized (ChocolateBoiler.class) {
                if (chocolateBoiler == null) {
                    chocolateBoiler = new ChocolateBoiler();
                }
            }
        }
        return chocolateBoiler;
    }

    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
        }
    }

    public void drain () {
        if (!isEmpty() && isBoiled()) {
            //
            empty = true;
        }
    }

    public void boil () {
        if (!isEmpty() && !isBoiled()) {
            //将炉内物煮沸
            boiled = true;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
