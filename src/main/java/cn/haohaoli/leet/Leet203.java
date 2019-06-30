package cn.haohaoli.leet;

/**
 * @author LiWenHao
 * @date 2019/6/28 14:38
 */
public class Leet203 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int[] arr){
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException();
            }
            this.val = arr[0];
            ListNode node = this;
            for (int i = 1; i < arr.length; i++) {
                node.next = new ListNode(arr[i]);
                node = node.next;
            }
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            ListNode cur = this;
            while (cur != null){
                res.append(cur.val).append("->");
                cur = cur.next;
            }
            res.append("NULL");
            return res.toString();
        }
    }

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
        ListNode list = leet203.new ListNode(new int[]{1, 2, 3, 4});
        ListNode listNode = leet203.removeElements3(list, 2);
        System.out.println(listNode);
    }


}
