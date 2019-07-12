package cn.haohaoli.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * 125. 验证回文串
 * https://leetcode-cn.com/problems/valid-palindrome/
 * @author LiWenHao
 * @date 2019-07-12 15:00
 */
public class Leet125 {

    public static boolean isPalindrome(String s) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            //大写
            if (charAt >= 65 && charAt <= 90) {
                characters.add(charAt);
            }
            if (charAt >= 97 && charAt <= 122) {
                charAt -= 32;
                characters.add(charAt);
            }
            //数字
            if (charAt >= 48 && charAt <= 57) {
                characters.add(charAt);
            }
        }

        //第一个等于最后一个以此类推
        for (int i = 0; i < characters.size() / 2; i++) {
            if (!characters.get(i).equals(characters.get(characters.size() - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}