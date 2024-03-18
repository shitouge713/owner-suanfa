package com.owner.suanfa.node;

/**
 * *合并两个有序链表**：将两个有序链表合并为一个新的有序链表并返回。示例：输入 1->2->4, 1->3->4，输出 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {
    public ListNodeDemo mergeTwoLists(ListNodeDemo l1, ListNodeDemo l2) {
        ListNodeDemo dummy = new ListNodeDemo(0);
        ListNodeDemo current = dummy;
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
        } else {
            current.next = l2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNodeDemo l1 = new ListNodeDemo(1);
        l1.next = new ListNodeDemo(2);
        l1.next.next = new ListNodeDemo(4);

        ListNodeDemo l2 = new ListNodeDemo(1);
        l2.next = new ListNodeDemo(3);
        l2.next.next = new ListNodeDemo(4);

        MergeTwoSortedLists solution = new MergeTwoSortedLists();
        ListNodeDemo mergedList = solution.mergeTwoLists(l1, l2);
        while (mergedList != null) {
            System.out.print(mergedList.val + " ");
            mergedList = mergedList.next;
        }
    }
}
