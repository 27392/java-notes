package cn.haohaoli.data.structure.linkedList;

/**
 * TODO 递归
 *  本质上,将原来的问题,转化成更小的同一问题
 *  递归函数的调用,本质就是函数调用
 *  只不过调用的函数是自己而已
 * @author LiWenHao
 * @date 2019-06-30 20:44
 */
public class Sum {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(sum(nums));
    }

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    /**
     * 递归
     * @param arr   数组
     * @param index 下标
     * @return
     */
    public static int sum(int[] arr, int index) {
        if (arr.length == index) return 0;              //求解最基本的问题
//        return arr[index] += sum(arr, index + 1);     //将原来的问题,转化成更小的同一问题
        int x = sum(arr, index + 1);
        int res = arr[index] + x;
        return res;
    }

}
