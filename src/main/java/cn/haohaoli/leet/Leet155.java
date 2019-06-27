package cn.haohaoli.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiWenHao
 * @date 2019/6/26 10:42
 */
public class Leet155 {

    private List<Integer> data;

    public Leet155() {
        this.data = new ArrayList<>();
    }

    public void push(int x) {
        this.data.add(x);
    }

    public void pop() {
        if (this.data.isEmpty()) {
            throw new IllegalArgumentException("stack is empty");
        }
        this.data.remove(data.size() - 1);
    }

    public int top() {
        if (this.data.isEmpty()) {
            throw new IllegalArgumentException("stack is empty");
        }
        return this.data.get(data.size() - 1);
    }

    public int getMin() {
        int min = top();
        for (Integer i : this.data) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }
}
