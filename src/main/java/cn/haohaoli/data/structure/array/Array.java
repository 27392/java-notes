package cn.haohaoli.data.structure.array;

/**
 * @author LiWenHao
 * @date 2019/6/19 17:58
 */
public class Array {

    private int[] data;

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
        data = new int[capacity];
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
        return data.length;
    }

    /**
     * 数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在最后面添加元素
     * @param e     元素
     */
    public void addLast(int e) {
        if (size == data.length) {
            throw new IllegalArgumentException("添加错误 数组容量已满");
        }
        data[size] = e;
        size++;
    }

    /**
     * 在最前面添加元素
     * @param e     元素
     */
    public void addFist(int e) {
        add(0, e);
    }

    /**
     * 添加
     * @param index     下标
     * @param e         元素
     */
    public void add(int index, int e) {
        if (size == data.length) {
            throw new IllegalArgumentException("添加错误 数组容量已满");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("添加错误 下标错误");
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }
}
