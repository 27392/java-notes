package cn.haohaoli.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * 155. 最小栈
 * https://leetcode-cn.com/problems/min-stack/
 * @author LiWenHao
 * @date 2019/6/26 10:42
 */
public class Leet155 {

    private List<Integer> data;

    private Integer min = Integer.MAX_VALUE;
    private Integer minIndex = -1;

    public Leet155() {
        data = new ArrayList<>();
    }

    public void push(int x) {
        data.add(x);
        if (min > x) {
            min = x;
            minIndex = data.size() - 1;
        }
    }

    public int pop() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("stack is empty");
        }
        int topIndex = data.size() - 1;
        Integer remove = data.remove(topIndex);
        if (minIndex == topIndex) {
            computeMin();
        }
        return remove;
    }

    public int top() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("stack is empty");
        }
        return data.get(data.size() - 1);
    }

    /**
     * 计算
     * @return
     */
    public void computeMin() {
        int minValue = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if (minValue > data.get(i)) {
                minValue = data.get(i);
                index = i;
            }
        }
        this.min = minValue;
        this.minIndex = index;
    }

    public int getMin(){
        return min;
    }

    public static void main(String[] args) {
        Leet155 leet155 = new Leet155();
        leet155.push(3);
        leet155.push(0);
        leet155.push(1);
        leet155.push(9);
        leet155.push(4);
        leet155.pop();
        leet155.pop();
        leet155.pop();
        leet155.pop();
        int min = leet155.getMin();
        System.out.println(min);
    }
}
