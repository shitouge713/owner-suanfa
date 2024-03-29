package com.owner.suanfa.node;

/**
 * 将一个无序的链表排升序排序，比如1->7-->6-->4-->5 输出1->4->5->6->7
 * 思路：归并排序
 * 归并排序因其在链表排序中的高效性而广泛使用。归并排序的基本思想是将链表分成最小单位（通常最小部分只有一个元素），然后逐步合并成有序的链表
 * 通过调整节点的指针来直接合并，除了少数几个临时变量，不需要额外的空间
 * 由于归并排序的时间复杂度为O(n log n)，它在处理大数据量时非常有效
 *
 */
public class LinkedListMergeSort {
    public static void main(String[] args) {
        // 创建链表 1->7->6->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(7);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(4);
        // 排序
        head = sortList(head);
        // 打印排序后的链表
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // 使用快慢指针找到中点
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 断开链表
        prev.next = null;
        // 对两部分分别进行排序
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        // 合并两个排序后的链表
        return merge(l1, l2);
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(0);
        ListNode current = temp;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        if (l1 != null) {
            current.next = l1;
        } else if (l2 != null) {
            current.next = l2;
        }
        return temp.next;
    }
}
