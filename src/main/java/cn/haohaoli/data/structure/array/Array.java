package cn.haohaoli.data.structure.array;

/**
 * @author LiWenHao
 * @date 2019/6/19 17:58
 */
public class Array {

    private int[] date;

    private int size;

    /**
     * 默认数量容量为10
     */
    public Array() {
        this(10);
    }

    /**
     * 传入数组的容量
     * @param capacity  容量
     */
    public Array(int capacity) {
        date = new int[capacity];
        size = 0;
    }

    /**
     * 获取数组中的元素个数
     * @return  元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     * @return  数组容量
     */
    public int getCapacity() {
        return date.length;
    }

    /**
     * 数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
