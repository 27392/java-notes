package cn.haohaoli.data.structure.array;

/**
 * @author LiWenHao
 * @date 2019/6/19 17:58
 */
public class Array<E> {

    private E[] data;

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
    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
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
    public void addLast(E e) {
        if (size == data.length) {
            resize(2 * data.length);
        }
        data[size] = e;
        size++;
    }

    /**
     * 在最前面添加元素
     * @param e     元素
     */
    public void addFist(E e) {
        add(0, e);
    }

    /**
     * 添加元素在index索引位置
     * @param index     下标
     * @param e         元素
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("添加错误 下标错误");
        }
        if (index == size) {
            resize(2 * data.length);
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
    public E get (int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("下标错误");
        }
        return data[index];
    }

    /**
     * 获取最后一个元素
     * @return  元素
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * 获取第一个元素
     * @return  元素
     */
    public E getFist(){
        return get(0);
    }

    /**
     * 修改index索引位置的元素
     * @param index 下标
     * @param e     元素
     */
    public void set(int index, E e) {
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
    public E remove (int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("下标错误");
        }
        E ret = get(index);
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //缩小
        if (size == data.length / 2) {
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删除第一个元素
     * @return  删除的元素
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return  删除的元素
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 删除元素
     * @param e 元素
     * @return  是否删除成功
     */
    public boolean removeElement(E e){
        int index = indexOf(e);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    /**
     * 是否包含
     * @param e 元素
     * @return  true 包含 / false 不包含
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
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
    public int indexOf (E e) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 调整大小
     * @param newCapacity   容量
     */
    @SuppressWarnings("unchecked")
    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
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
