package cn.haohaoli.data.structure.linkedList;

/**
 * TODO 递归
 *  本质上,将原来的问题,转化成更小的同一问题
 * @author LiWenHao
 * @date 2019-06-30 20:44
 */
public class Sum {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
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
        if (arr.length == index) {                  //求解最基本的问题
            return 0;
        }
        //切记 不能使用index++; index++ 是先用在加 ++index 是先加在用
        //return arr[index] += sum(arr, index++);
        return arr[index] += sum(arr, ++index);     //将原来的问题,转化成更小的同一问题
    }

}
