package com.owner.suanfa.node;

/**
 * 判断链表是否有环
 */
public class WhetherListNodeHasRing {
    int val;
    WhetherListNodeHasRing next;

    WhetherListNodeHasRing(int x, WhetherListNodeHasRing node) {
        val = x;
        next = node;
    }

    public static void main(String[] args) {
        WhetherListNodeHasRing node5 = new WhetherListNodeHasRing(5, null);
        WhetherListNodeHasRing node4 = new WhetherListNodeHasRing(4, node5);
        WhetherListNodeHasRing node3 = new WhetherListNodeHasRing(3, node4);
        WhetherListNodeHasRing node2 = new WhetherListNodeHasRing(2, node3);
        WhetherListNodeHasRing node1 = new WhetherListNodeHasRing(1, node2);
        node5.next = node3;
        System.out.println(hasLoop(node1));
    }

    /**
     * 使用快慢指针的方式，一个指针每次移动2步，一个指针每次移动一步
     * 如果链表中有环，两个指针最终会相遇；如果没有环，快指针会先到达链表尾部
     * 时间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public static boolean hasLoop(WhetherListNodeHasRing head) {
        if (head == null || head.next == null) {
            return false;
        }
        WhetherListNodeHasRing slow = head;
        WhetherListNodeHasRing fast = head.next;
        while (slow != fast) {
            //如果快的为null或快的next为null，表示没有环
            if (fast == null || fast.next == null) {
                return false;
            }
            //慢的每次移动1位
            slow = slow.next;
            //快的每次移动2位
            fast = fast.next.next;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListNode02{");
        sb.append("val='").append(val).append('\'');
        sb.append(", next='").append(next).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
