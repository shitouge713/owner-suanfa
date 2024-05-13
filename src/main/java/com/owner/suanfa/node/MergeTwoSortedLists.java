package com.owner.suanfa.node;

/**
 * *合并两个有序链表**：将两个有序链表合并为一个新的有序链表并返回。示例：输入 1->2->4, 1->3->4，输出 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(4);
        node1.next.next.next = new ListNode(6);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);
        ListNode mergedList = mergeTwoLists(node1, node2);
        // 打印排序后的链表
        while (mergedList != null) {
            System.out.print(mergedList.val + " ");
            mergedList = mergedList.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /**
         * finalNode 用于存放最终的链表
         * sentinel.next表示最后的结果
         * temp 用于过程操作的链表，用于移动，指向next等操作
         * temp 要指向result
         */
        // 创建一个哨兵节点，可以简化插入操作
        ListNode finalNode = new ListNode(0);
        // 用于追踪新链表的末尾
        ListNode temp = finalNode;
        // 当两个链表都不为空时，进行比较并合并
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        // 如果l1或l2中还有剩余节点，直接连接到新链表的末尾
        if (l1 != null) {
            temp.next = l1;
        } else if (l2 != null) {
            temp.next = l2;
        }
        // 返回哨兵节点的下一个节点，即新链表的头节点
        return finalNode.next;
    }

}
