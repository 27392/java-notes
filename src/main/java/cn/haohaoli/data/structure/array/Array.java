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
     * 添加元素在index索引位置
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
            //后一个索引赋值当前索引对应的元素
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取index索引位置的元素
     * @param index 下标
     * @return      元素
     */
    public int get (int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("下标错误");
        }
        return data[index];
    }

    /**
     * 修改index索引位置的元素
     * @param index 下标
     * @param e     元素
     */
    public void set(int index, int e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("下标错误");
        }
        data[index] = e;
    }

    /**
     * 删除index索引位置的元素
     * @param index 下标
     * @return      删除的元素
     */
    public int remove (int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("下标错误");
        }
        int ret = get(index);
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return ret;
    }

    /**
     * 删除第一个元素
     * @return  删除的元素
     */
    public int removeFirst(){
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return  删除的元素
     */
    public int removeLast(){
        return remove(size - 1);
    }

    /**
     * 删除元素
     * @param e 元素
     * @return  是否删除成功
     */
    public boolean removeElement(int e){
        int index = indexOf(e);
        if (index == -1) {
            return false;
        }
        remove(e);
        return true;
    }

    /**
     * 是否包含
     * @param e 元素
     * @return  true 包含 / false 不包含
     */
    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 元素所在下标
     * @param e 元素
     * @return  下标 (不存在返回 -1)
     */
    public int indexOf (int e) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size = %d , capacity = %d \n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
