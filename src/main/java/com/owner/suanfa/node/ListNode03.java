package com.owner.suanfa.node;

/**
 * 删除链表的倒数第n个元素
 */
public class ListNode03 {
    int val;
    ListNode03 next;
    ListNode03(int x) {
        val = x;
    }
    ListNode03(int x, ListNode03 node) {
        val = x;
        next = node;
    }

    public static ListNode03 removeNthFromEnd(ListNode03 head, int n) {
        ListNode03 dummy = new ListNode03(0);
        dummy.next = head;
        ListNode03 fast = dummy;
        ListNode03 slow = dummy;
        // 让快指针先移动n+1步
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        // 快慢指针同时移动，直到快指针到达末尾
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除第n个节点
        slow.next = slow.next.next;

        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode03 node5 = new ListNode03(5,null);
        ListNode03 node4 = new ListNode03(4,node5);
        ListNode03 node3 = new ListNode03(3,node4);
        ListNode03 node2 = new ListNode03(2,node3);
        ListNode03 node1 = new ListNode03(1,node2);
        System.out.println(removeNthFromEnd(node1,2));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListNode03{");
        sb.append("val='").append(val).append('\'');
        sb.append(", next='").append(next).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
