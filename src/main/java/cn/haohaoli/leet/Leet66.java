package cn.haohaoli.leet;

import java.util.Arrays;

/**
 * 66. 加一
 * https://leetcode-cn.com/problems/plus-one/comments/
 * @author LiWenHao
 * @date 2019-07-12 10:10
 */
public class Leet66 {

    public static int[] plusOne(int[] digits) {
        boolean add = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            //第一个
            if (i == digits.length - 1) {
                if (digits[digits.length - 1] == 9) {
                    digits[digits.length - 1] = 0;
                    add = true;
                } else {
                    digits[digits.length - 1] = digits[digits.length - 1] + 1;
                    add = false;
                }
                continue;
            }
            if (add) {
                if (digits[i] == 9) {
                    digits[i] = 0;
                    add = true;
                } else {
                    digits[i] = digits[i] + 1;
                    add = false;
                }
            }
        }
        if (digits[0] == 0) {
            int[] ints = new int[digits.length + 1];
            ints[0] = 1;
            for (int i = 1; i < ints.length; i++) {
                ints[i] = digits[i - 1];
            }
            return ints;
        }
        return digits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{9})));
    }
}