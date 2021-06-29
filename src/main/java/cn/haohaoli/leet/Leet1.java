package cn.haohaoli.leet;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @author lwh
 */
public class Leet1 {

    /*
    给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

    你可以按任意顺序返回答案

     */

    /*
    示例 1：
        输入：nums = [2,7,11,15], target = 9
        输出：[0,1]
        解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

    示例 2：
        输入：nums = [3,2,4], target = 6
        输出：[1,2]

    示例 3：
        输入：nums = [3,3], target = 6
        输出：[0,1]

     */

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.twoSum1(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(solution.twoSum1(new int[]{3, 2, 4}, 6)));

        System.out.println(Arrays.toString(solution.twoSum2(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(solution.twoSum2(new int[]{3, 2, 4}, 6)));

    }

    static class Solution {

        /**
         * 暴力枚举
         *
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSum1(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = nums.length - 1; j > i; j--) {
                    if (nums[j] + nums[i] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            throw new IllegalArgumentException();
        }

        /**
         * 哈希表
         *
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSum2(int[] nums, int target) {

            int len = nums.length;

            HashMap<Integer, Integer> map = new HashMap<>(len);
            map.put(nums[0], 0);

            for (int i = 1; i < len; i++) {
                int     num   = nums[i];
                Integer index = map.get(target - num);
                if (null != index) {
                    return new int[]{index, i};
                }
                map.put(num, i);
            }
            throw new IllegalArgumentException();
        }
    }
}
