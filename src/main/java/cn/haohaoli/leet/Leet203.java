package cn.haohaoli.leet;

/**
 * 203. 移除链表元素
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @author LiWenHao
 * @date 2019/6/28 14:38
 */
public class Leet203 {

    public ListNode removeElements(ListNode head, int val) {
        //删除头节点所有符合的
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        while (prev.next != null){
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    /**
     * 使用虚拟头节点
     */
    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null){
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 使用递归
     */
    public ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        //递归解决删除这个更小链表中对应的元素
        head.next = removeElements3(head.next, val);
        return head.val == val ? head.next : head;
        /*
        ListNode listNode = removeElements3(head.next, val);
        if (head.val == val) {
            return listNode;
        } else {
            head.next = listNode;
            return head;
        }
        */

    }

    public static void main(String[] args) {
        Leet203 leet203 = new Leet203();
        ListNode list = new ListNode(new int[]{1, 2, 3, 4});
        ListNode listNode = leet203.removeElements3(list, 2);
        System.out.println(listNode);
    }


}
