package cn.haohaoli.leet;


import java.util.Stack;

/**
 * 1021. 删除最外层的括号
 * https://leetcode-cn.com/problems/remove-outermost-parentheses/
 * @author LiWenHao
 * @date 2019-08-15 08:24
 */
public class Leet1021 {

    /**
     * 输入："(()()) (()) (()(()))"
     * 输出："()()()()(())"
     * 解释：
     * 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
     * 删除每隔部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
     */
    public static void main(String[] args) {
        System.out.println(removeOuterParentheses("()()()"));
    }

    public static String removeOuterParentheses(String S) {
        if ("".equals(S)) {
            return "";
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int index = -1;

        for (int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == '(') {
                if (stack.isEmpty()) {
                    index = i;
                }
                stack.push(S.charAt(i));
            }
            if (S.charAt(i) == ')') {
                stack.pop();
            }
            if (stack.isEmpty()) {
                sb.append(S, index + 1, i);
            }
        }
        return sb.toString();
    }




}
