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

}
