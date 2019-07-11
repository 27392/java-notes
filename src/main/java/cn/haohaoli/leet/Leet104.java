package cn.haohaoli.leet;

/**
 * 104. 二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/submissions/
 * @author LiWenHao
 * @date 2019-07-11 21:34
 */
public class Leet104 {

    public int maxDepth(TreeNode root) {
        return maxDepth(root,0);
    }

    public int maxDepth(TreeNode root,int depth) {
        if (null == root) {
            return depth;
        }
        int left = maxDepth(root.left,depth + 1);
        int right = maxDepth(root.right,depth + 1);
        return left > right ? left: right;
    }

}
