package cn.haohaoli.leet;

import java.util.Stack;

/**
 * @author LiWenHao
 * @date 2019/6/25 16:14
 */
public class Leet20 {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
                continue;
            }
            if (stack.empty()) {
                return false;
            }
            Character pop = stack.pop();
            if (c == '}' && pop != '{') {
                return false;
            }
            if (c == ']' && pop != '[') {
                return false;
            }
            if (c == ')' && pop != '(') {
                return false;
            }
        }
        return stack.isEmpty();
    }

}
