package com.owner.suanfa.node;

/**
 * 删除链表的倒数第n个元素
 */
public class DeleteListNodeOfN {
    int val;
    DeleteListNodeOfN next;
    DeleteListNodeOfN(int x) {
        val = x;
    }
    DeleteListNodeOfN(int x, DeleteListNodeOfN node) {
        val = x;
        next = node;
    }

    public static DeleteListNodeOfN removeNthFromEnd(DeleteListNodeOfN head, int n) {
        DeleteListNodeOfN dummy = new DeleteListNodeOfN(0);
        dummy.next = head;
        DeleteListNodeOfN fast = dummy;
        DeleteListNodeOfN slow = dummy;
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
        DeleteListNodeOfN node5 = new DeleteListNodeOfN(5,null);
        DeleteListNodeOfN node4 = new DeleteListNodeOfN(4,node5);
        DeleteListNodeOfN node3 = new DeleteListNodeOfN(3,node4);
        DeleteListNodeOfN node2 = new DeleteListNodeOfN(2,node3);
        DeleteListNodeOfN node1 = new DeleteListNodeOfN(1,node2);
        System.out.println(removeNthFromEnd(node1,5));
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
