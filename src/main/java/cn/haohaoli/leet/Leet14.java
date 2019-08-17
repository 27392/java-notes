package cn.haohaoli.leet;

/**
 * 14. 最长公共前缀
 * https://leetcode-cn.com/problems/longest-common-prefix/
 * @author LiWenHao
 * @date 2019-08-17 18:32
 */
public class Leet14 {

    /**
     * 第一种实现
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {

        if (null == strs || strs.length == 0) {
            return "";
        }

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 第二种实现
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {

        String p = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(p)) {
                p = p.substring(0, p.length() - 1);
                i--;
            }
        }
        return p;
    }



    public static void main(String[] args) {
        System.out.println(longestCommonPrefix2(new String[]{"flower","flow","flight"}));
    }
}
