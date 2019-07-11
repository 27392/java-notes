package cn.haohaoli.leet;

/**
 * 237. 删除链表中的节点
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/submissions/
 * @author LiWenHao
 * @date 2019-07-11 22:25
 */
public class Leet237 {

    //需要仔细看题 有点打脑壳
    public void deleteNode(ListNode node) {
        node.val = node.next.val;   //下一个节点的值等于现在节点的值
        node.next = node.next.next; //删除下一个节点
    }

}
