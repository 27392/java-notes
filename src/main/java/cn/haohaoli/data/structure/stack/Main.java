package cn.haohaoli.data.structure.stack;

/**
 * @author LiWenHao
 * @date 2019-06-24 23:46
 */
public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);

    }
}
